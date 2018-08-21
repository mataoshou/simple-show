package sample.html.content;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sample.html.bean.HtmlItem;
import sample.html.bean.HtmlPackage;


public class HtmlGet {

	String charset ="";
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	
	public String getContent(String url) throws Exception
	{
		doGet(url);
		
		if(charset.equals(""))
		{
			charset = getMetaChartset(outputStream.toString());
		}
		System.out.println("..................编码格式："+charset);
		
		String htmlContent = outputStream.toString(charset);
		return htmlContent;
	}
	
	public String getMetaChartset(String str) throws SAXException, IOException
	{
		String reg  = "<meta(.*?)>";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find())
		{
			String content = matcher.group(1);
			if(content.indexOf("charset")>=0)
			{
				String[] charts = getCharts();
				for(int i=0;i<charts.length;i++)
				{
					if(content.toUpperCase().indexOf(charts[i])>=0)
					{
						return charts[i];
					}
				}
			}
		}		
		
		return "";
	}
	
	public String[] getCharts()
	{
		String[] charts = new String[]{"GBK","GB2312","UTF-8"};
		return charts;
	}
	
	// HTTP GET测试
	public String doGet(String url) throws Exception
	{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse response = httpclient.execute(httpget);
		try
		{
			StatusLine statusLine = response.getStatusLine();
			int status = statusLine.getStatusCode();
			if(status != 200)
			{
				throw new Exception("HTTP GET出错:"+ status + ","+ statusLine.getReasonPhrase());
			}

			String content = response.getLastHeader("Content-Type").getValue();
			this.charset = getContentTypeChartSet(content);
			HttpEntity entity = response.getEntity();
			if (entity != null)
			{
				InputStream in = entity.getContent();
				int n=0;
				byte[] bs =new byte[1024*10];
				while(true)
				{
					
					n= in.read(bs, 0, 10240);
					if(n<0)break;
					System.out.println("....."+n);
					outputStream.write(bs, 0, n);
				}
			}
		} finally
		{
			response.close();
		}
		return null;
	}
	
	public String getContentTypeChartSet(String content) throws Exception
	{
		String[] charts = getCharts();
		for(int i=0;i<charts.length;i++)
		{
			if(content.toUpperCase().indexOf(charts[i])>=0)
			{
				return charts[i];
			}
		}
		return "";
	}
	

}
