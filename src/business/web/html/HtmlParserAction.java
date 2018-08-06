package business.web.html;

import net.sf.json.JSONObject;
import sample.html.bean.HtmlItem;
import sample.html.content.HtmlDraw;
import sample.html.content.HtmlUtils;
import frame.mtfilter.MtAction;

public class HtmlParserAction  extends MtAction {

	@Override
	public void initAction() {
		
	}

	@Override
	public String excute(String content) {
		JSONObject json = JSONObject.fromObject(content);
		String url = json.getString("url");
		
		JSONObject result = JSONObject.fromObject("{}");
		try {
			String htmlContent = HtmlUtils.doGet(url);
			
			String charset = HtmlUtils.getChartset(htmlContent);
			System.out.println("..................编码格式："+charset);
			
			htmlContent = new String( htmlContent.getBytes("UTF-8"),charset);
			System.out.println(htmlContent);
			HtmlDraw d = new HtmlDraw();
			d.draw(htmlContent);
			String resultContent ="";
			for(HtmlItem item: d.p.items)
			{
				resultContent += item.content +"<br>";
			}
			result.put("content", resultContent);
			result.put("title", d.p.title);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result.toString();
	}

	@Override
	public boolean ischeck() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static void main(String[] args)
	{
		HtmlParserAction action = new HtmlParserAction();
		JSONObject json = JSONObject.fromObject("{}");
		json.put("url", "https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_18177225264736531237%22%7D&n_type=0&p_from=1");
		 
	    System.out.println(	action.excute(json.toString()));
	}

}
