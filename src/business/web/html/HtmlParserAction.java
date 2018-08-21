package business.web.html;

import net.sf.json.JSONObject;
import sample.html.bean.HtmlItem;
import sample.html.content.HtmlDraw;
import sample.html.content.HtmlGet;
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

			HtmlDraw d = new HtmlDraw();
			d.getContent(url);
			System.out.println(d.content);
			d.draw(d.content);
			String resultContent ="";
			for(HtmlItem item: d.p.items)
			{
				resultContent += item.content +"\r\n";
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
		json.put("url", "http://sports.qq.com/a/20180819/030010.htm");
		 
	    System.out.println(	action.excute(json.toString()));
	}

}
