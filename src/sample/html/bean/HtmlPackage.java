package sample.html.bean;

import java.util.ArrayList;
import java.util.List;

import sample.html.content.HtmlCommon;
import sample.html.tool.ListUtils;

public class HtmlPackage {
	public String title ="";
	public List<HtmlItem> items= new ArrayList();
	
	public RangeItem range = new RangeItem();
	
	public int maxLength =0;
	public int smaxLength = 0;
	public int minLength = 0;
	public int sminLength = 0;
	
	public int middleScore = 50;
	
	public int interval = 0;
	
	
	///根据分数段进行评分
	public void initScore()
	{
		List<Integer> lengths = new ArrayList();
		
		for(HtmlItem item : items)
		{
			if(item.length==0)continue;
			lengths.add(item.length);
		}
		
		maxLength = ListUtils.removeMax(lengths);
		minLength = ListUtils.removeMin(lengths);
		
		smaxLength = ListUtils.getMax(lengths);
		sminLength = ListUtils.getMin(lengths);
		
		int interval = (smaxLength- sminLength)/100;
		
		if((smaxLength- sminLength)%100>0)interval++;
		
		this.interval = interval;
		
		System.out.println(String.format("最大值:%s,最小值：%s,切割区间范围：%s", smaxLength,sminLength,interval));
		
		////构建基础分值
		for(HtmlItem item : items)
		{
			item.score = ( (item.length -sminLength)/interval);
			item.finalScore = item.score;
		}
		
		buildRange();
		
		HtmlCommon.show(items, "分数初始化结果");
	}
	
	public void getMiddle()
	{
		middleScore = ((smaxLength - sminLength)/interval)/2;
	}
	
	public void buildRange(int middle)
	{
		int length = 0;
		int index = 0;
		int end = 0;
		
		
		RangeItem r = new RangeItem();
		
		int size = items.size();
		for(int i =0;i<size;i++)
		{
			if(items.get(i).score>middle)
			{
				length++;
				if(index<=0)index = i;
				if(i!=size-1)
				{
					continue;
				}
				else{
					i++;
				}
			}
			if(index>0)
			{
				end = i-1;
				if(r.length<length)
				{
					r.index = index;
					r.end = end;
					r.length = length;
					
				}
				length = 0;
				index = 0;
				end = 0;
			}
		}
		this.range = r;
		for(int i = range.index;i<range.length;i++)
		{
			items.get(i).unDelete = true;
		}
		
		System.out.println(String.format("最大范围  起始位置 %s  结束位置 %s  长度 %s  中间分值 %s",range.index,range.end,range.length ,this.middleScore));
	
	}
	
	public void buildRange()
	{
		buildRange(middleScore);
	}
}
