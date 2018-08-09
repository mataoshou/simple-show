package sample.html.bean;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public void initfinalScore()
	{
		int length = 0;
		int index = 0;
		int end = 0;
		
		middleScore = ((smaxLength - sminLength)/interval)/2;
		
		for(int i =0;i<items.size();i++)
		{
			if(items.get(i).score>middleScore)
			{
				length++;
				if(index<=0)index = i;
				continue;
			}
			if(index>0)
			{
				end = i-1;
				if(range.length<length)
				{
					range.index = index;
					range.end = end;
					range.length = length;
					
				}
				length = 0;
				index = 0;
				end = 0;
			}
		}
		
		for(int i = range.index;i<range.length;i++)
		{
			items.get(i).unDelete = true;
		}
		
		System.out.println(String.format("最大范围  起始位置 %s  结束位置 %s  长度 %s  中间分值 %s",range.index,range.end,range.length ,this.middleScore));
	}
}
