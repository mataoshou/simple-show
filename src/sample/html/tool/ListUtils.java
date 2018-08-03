package sample.html.tool;

import java.util.List;

public class ListUtils {
	public static int getMax(List<Integer> list)
	{
		int max = 0;
		int index =0;
		for(Integer i:list)
		{
			if(i> max)
			{
				max = i;
				index = i;
			}
		}
		return max;
	}
	
	public static int removeMax(List<Integer> list)
	{
		int max = 0;
		
		for(int i=0;i<list.size();i++)
		{
			int cur = list.get(i);
			if(cur>max)
			{
				max = cur;
			}
		}
		
		for(int i=0;i<list.size();i++)
		{
			int cur = list.get(i);
			if(cur == max)
			{
				list.remove(i);
			}
		}
		
		return max;
	}
	
	
	public static int getMin(List<Integer> list)
	{
		int min = Integer.MAX_VALUE;
		for(Integer i:list)
		{
			if(i < min)
			{
				min = i;
			}
		}
		return min;
	}
	
	public static int removeMin(List<Integer> list)
	{
		int min = Integer.MAX_VALUE;
		
		for(int i=0;i<list.size();i++)
		{
			int cur = list.get(i);
			if(cur < min)
			{
				min = cur;
			}
		}
		for(int i=0;i<list.size();i++)
		{
			int cur = list.get(i);
			if(cur == min)
			{
				list.remove(i);
			}
		}
		return min;
	}
}
