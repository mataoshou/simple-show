package sample.html.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sample.html.bean.HtmlItem;
import sample.html.bean.HtmlPackage;
import sample.html.tool.ListUtils;

public class HtmlDraw {
	public HtmlPackage p = new HtmlPackage();
	public HtmlGet html = new HtmlGet();
	public String content ="";
	public void getText(String content) throws Exception
	{
		
		p  = HtmlCommon.getPackage(content);
	}
	
	
	public void getContent(String url) throws Exception
	{
		content = html.getContent(url);
	}
	///根据分数段进行评分
	public void initScore()
	{
		p.initScore();
		
	}
	//清理不符合条件的两侧文本
	public void filter(int score)
	{
		System.out.println("....清理两侧分值小于  "+ score+"  的文本区域");
		int length = p.items.size();
		int begin =0;
		int end =0;
		for(int i =0;i<length;i++)
		{
			HtmlItem item = p.items.get(i);
			if(item.finalScore >= score)
			{
				break;
			}
			begin ++;
		}
		
		for(int i=length-1;i>begin;i--)
		{
			HtmlItem item = p.items.get(i);
			if(item.finalScore >= score)
			{
				
				break;
			}
			end++;
		}
		
		for(int i =0;i<begin;i++)
		{
			p.items.remove(0);
		}
		for(int i=0;i<end;i++)
		{
			int l = p.items.size();
			p.items.remove(l-1);
		}
		
	}
	
//	public RangeItem getMaxRange()
//	{
//		
//	}
	
	///清理不符合条件的结束文本
	public void filter2(int score)
	{
		System.out.println("....清理分值小于  "+ score+"  的文本区域");
		int index  =0;
		int max =0;
		int length = p.items.size();
		int l_index = p.range.index;
		for(int i =0;i<l_index;i++)
		{
			HtmlItem item = p.items.get(i);
			if(item.length>max)
			{
				index =i;
				max =item.length;
			}
		}
		
		
		int begin =index;
		for(int i =index;i>=0;i--)
		{
			HtmlItem item = p.items.get(i);
			if(item.finalScore <= score)
			{
				break;
			}
			begin--;
		}
		int end =p.range.end;
		for(int i =p.range.end;i<length;i++)
		{
			HtmlItem item = p.items.get(i);
			if(item.finalScore <= score)
			{
				break;
			}
			end++;
		}
		
		end =length - end;
		

		for(int i=0;i<begin;i++)
		{
			p.items.remove(0);
		}
		
		for(int i=0;i<end;i++)
		{
			int l = p.items.size()-1;
			p.items.remove(l);
		}
	}
	//低于分数的文本清理
	public void filter3(int score)
	{
		System.out.println("....清理分值小于  "+ score +"  的文本");
		int length = p.items.size();
		for(int i =0;i<length;i++)
		{
			HtmlItem item = p.items.get(i);
			if(item.finalScore<=score)
			{
				p.items.remove(i);
				i--;
				length = p.items.size();
			}
		}
	}
	////根据左右的内容修改评分
	public void buildFinalScore()
	{
		int length = p.items.size();
		HtmlItem pre = null;
		HtmlItem last = null;
		
		for(int i=0;i<p.items.size();i++)
		{
			if(i!=0)
			{
				pre = p.items.get(i-1);
			}
			if(i!=(length-1))
			{
				last = p.items.get(i+1);
			}
			
			HtmlItem item = p.items.get(i);
			if(pre!=null)
			{
				item.finalScore += pre.score;
			}
			
			if(last!=null)
			{
				item.finalScore += last.score;
			}
		}
		
		HtmlCommon.show(p.items, "分数合并结果");
	}
	
	public void draw(String content) throws Exception
	{
		
		getText(content);
		System.out.println(".........................标题："+p.title);
		initScore();
		filter(10);
		p.buildRange();
		System.out.println(".........................................................");
		HtmlCommon.show(p.items, "第一次分数整理后结果");
		System.out.println(".........................................................");
		filter3(0);
		p.buildRange();
		buildFinalScore();
		p.buildRange();
		filter2(10);
		p.buildRange();
		filter(20);
		p.buildRange();
		System.out.println(".........................................................");
		HtmlCommon.show(p.items, "第二次分数整理后结果");
	}
	
	
	public static void main(String[] args) throws Exception
	{
		File f =  new File(HtmlDraw.class.getResource("/").getPath(),"sample/html/111.html");
		HtmlDraw d = new HtmlDraw();
		
	}
}
