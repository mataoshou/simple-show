package sample.html.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sample.html.bean.HtmlItem;
import sample.html.bean.HtmlPackage;
import sample.html.tool.ListUtils;

public class HtmlDraw {
	public HtmlPackage p = new HtmlPackage();
	public void getText(String content) throws Exception
	{
		
		p  = HtmlUtils.getPackage(content);
	}
	
	///根据分数段进行评分
	public void initScore()
	{
		List<Integer> lengths = new ArrayList();
		
		for(HtmlItem item : p.items)
		{
			if(item.length==0)continue;
			lengths.add(item.length);
		}
		
		p.maxLength = ListUtils.removeMax(lengths);
		p.minLength = ListUtils.removeMin(lengths);
		
		p.smaxLength = ListUtils.getMax(lengths);
		p.sminLength = ListUtils.getMin(lengths);
		
		
		int interval = (p.smaxLength- p.sminLength)/100;
		
		if((p.smaxLength- p.sminLength)%100>0)interval++;
		
		p.interval = interval;
		
		System.out.println(String.format("最大值:%s,最小值：%s,切割区间范围：%s", p.smaxLength,p.sminLength,interval));
		
		////构建基础分值
		for(HtmlItem item : p.items)
		{
			item.score = ( (item.length -p.sminLength)/interval);
			item.finalScore = item.score;
		}
		
		p.initfinalScore();
		
		HtmlUtils.show(p.items, "分数初始化结果");
		
	}
	//清理不符合条件的两侧文本
	public void filter(int score)
	{
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
		
		p.range.index-=begin;
		p.range.end -=begin;
	}
	
//	public RangeItem getMaxRange()
//	{
//		
//	}
	
	///清理不符合条件的结束文本
	public void filter2(int score)
	{
		int index  =0;
		int max =0;
		int length = p.items.size();
		for(int i =0;i<p.range.index;i++)
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
		
		HtmlUtils.show(p.items, "分数合并结果");
	}
	
	////根据左右的内容，清理异常数据
	public void thirdDraw()
	{
		
	}
	
	public void draw(String content) throws Exception
	{
		
		getText(content);
		System.out.println(".........................标题："+p.title);
		initScore();
		filter(10);
		System.out.println(".........................................................");
		HtmlUtils.show(p.items, "第一次分数整理后结果");
		System.out.println(".........................................................");
		filter3(0);
		buildFinalScore();
		filter2(10);
		filter(20);
		System.out.println(".........................................................");
		HtmlUtils.show(p.items, "第二次分数整理后结果");
	}
	
	
	public static void main(String[] args) throws Exception
	{
		File f =  new File(HtmlDraw.class.getResource("/").getPath(),"sample/html/666.html");
		HtmlDraw d = new HtmlDraw();
		
		String content = HtmlUtils.getString(f, "UTF-8");
//		System.out.println(content);
		String charset = HtmlUtils.getChartset(content);
//		
		content = HtmlUtils.getString(f, charset);
		d.draw(content);
		
	}
}
