package sample.html.content;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import sample.html.bean.HtmlItem;
import sample.html.bean.HtmlPackage;

public class HtmlCommon {
	public static String getString(File dstFile,String charset) throws Exception
	{
		InputStream inStream = new FileInputStream(dstFile);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try
		{
			byte[] buf = new byte[4096];
			while (true)
			{
				int n = inStream.read(buf);
				if (n <= 0)
					break;
				out.write(buf, 0, n);
			}
		} finally
		{
			inStream.close();
		}
		return out.toString(charset);
	}
	
	public static void show(List<HtmlItem> list,String descr)
	{
		int index =0;
		for(HtmlItem item :list)
		{
			index++;
			System.out.println(String.format("index : %s [ %s ] 分值 ：%s 内容： %s", index, descr,item.finalScore,item.content));
		}
	}
	
	public static HtmlPackage getPackage(String content) throws SAXException, IOException
	{
		DOMParser parser = new DOMParser();
		parser.parse(new InputSource(new StringReader(content)));
		Document doc = parser.getDocument();
		Node titleN = doc.getElementsByTagName("title").item(0);
		Node body = doc.getElementsByTagName("BODY").item(0);
		List<HtmlItem> list = new ArrayList();
		body.getTextContent();
		getItem(body,list);
		
		
		HtmlPackage p = new HtmlPackage();
		p.title = titleN.getTextContent();
		p.items = list;
		return p;
	}
	
	public static List getItem(Node root,List list)
	{
		// 若是文本节点的话，直接返回
		if (root.getNodeType() == Node.TEXT_NODE)
		{
			String text = root.getNodeValue().trim();
			if(text!=null&&text.length()>0)
			{
				HtmlItem item = new HtmlItem(text);
				list.add(item);
			}
			return list;
		}
		if (root.getNodeType() == Node.ELEMENT_NODE)
		{
			Element elmt = (Element) root;
			// 抛弃脚本
			if (elmt.getTagName().equals("STYLE")
					|| elmt.getTagName().equals("SCRIPT"))
			{
				return list;
			}
			String style = elmt.getAttribute("style");
			if (style != null && style.indexOf("display:none") >= 0)
			{
				return list;
			}

			NodeList children = elmt.getChildNodes();
			for (int i = 0; i < children.getLength(); i++)
			{
				getItem(children.item(i),list);
			}
		}
		
		return list;
	}
}
