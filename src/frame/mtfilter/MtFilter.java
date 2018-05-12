package frame.mtfilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MtFilter implements Filter
{

	Map<String, ApiItem> class_map = new HashMap();

	@Override
	public void destroy()
	{

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String result = "";
		try
		{
			result = execute(httpRequest, httpResponse);// 执行函数
		} catch (Exception e)
		{
			httpResponse.sendError(400, "eorror:" + e.getMessage());
			return;
		}

		byte[] b = result.getBytes("UTF-8");
		httpResponse.setContentType("text/plain");
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentLength(b.length);

		ServletOutputStream out = response.getOutputStream();
		out.write(b);
		out.close();
	}

	// 运行主体函数
	String execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		String reply = getJson(request);// 获取json数据
		String url = request.getRequestURI();// 访问的url路径

		String key = getKey(url);// 获取key值
		ApiItem item = class_map.get(key);// 获取类的名称
		if (item == null)
		{
			throw new Exception("未找到接口 " + key + ".mt");
		}
		String result = "";
		Class c = Class.forName(item.className);// 获取类
		Object action = c.newInstance();// 构建类的对象
		if (action instanceof MtAction)// 检查是够符合要求
		{
			// 初始化
			Method init = c.getMethod("init", HttpServletRequest.class,
					HttpServletResponse.class);
			boolean status = (Boolean) init.invoke(action, request, response);

			initParams(request, action, c);

			String methodName = item.method;
			// 运行
			Method exec = c.getMethod(methodName, String.class);
			Object data = exec.invoke(action, reply);
			result = data.toString();
			// System.out.println(result);
		}
		return result;
	}

	// 初始化参数
	void initParams(HttpServletRequest request, Object obj, Class c)
	{
		Map map = request.getParameterMap();

		Set<String> set = map.keySet();
		Method[] methods = c.getMethods();
		for (String param : set)// 遍历参数
		{
			try
			{
				String setMethodName = MtTool.getSetMethodName(param);
				for (int i = 0; i < methods.length; i++)// 遍历函数，找到set方法
				{
					Method m = methods[i];
					if (m.getName().equals(setMethodName))
					{
						Class[] ts = m.getParameterTypes();
						if (ts.length != 1)
							continue;
						// System.out.println(ts[0].toString());

						Object paramValue = MtTool.convertByType(
								request.getParameter(param), ts[0]);
						m.invoke(obj, paramValue);
					}
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	// 获取key值
	String getKey(String url)
	{
		int p1 = url.lastIndexOf("/");
		String last = url.substring(p1 + 1);
		int p2 = last.lastIndexOf(".");
		String key = last.substring(0, p2);
		return key;
	}

	// 获取json数据流
	public String getJson(ServletRequest request) throws IOException
	{
		ServletInputStream reader = request.getInputStream();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024 * 10];
		int n;
		while ((n = reader.read(b)) != -1)
		{
			out.write(b, 0, n);
		}
		String result = out.toString();// 上传的json数据
		// System.out.println(result);
		return result;
	}

	// 初始化键值对，获取存储到map中
	@Override
	public void init(FilterConfig config) throws ServletException
	{
		InputStream st = this.getClass().getResourceAsStream("/config/mt.xml");

		SAXReader reader = new SAXReader();
		try
		{
			Document xmldoc = reader.read(st);
			Element root = xmldoc.getRootElement();
			st.close();
			List<Element> apis = root.elements("mt-api");// 获取所有的action
			for (Element a : apis)
			{
				List items = a.elements("action");
				for (int i = 0; i < items.size(); i++)
				{
					Element e = (Element) items.get(i);
					String name = e.attribute("name").getText();// 获取name属性值
					String cl = e.attribute("class").getText();// 获取class属性值
					ApiItem item =new ApiItem();
					item.name = name;
					item.className = cl;
					item.method="excute";
					if ( e.attribute("method")!= null)
					{
						item.method = e.attribute("method").getText();// 获取class属性值
					}

					class_map.put(name, item);// 添加类对象的键值对
				}
			}
		} catch (DocumentException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	class ApiItem{
		String name;
		String className ;
		String method;
	}
}
