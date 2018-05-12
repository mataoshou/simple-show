package tool.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils
{
	//判断string是否为有效字符串
	public  boolean isEmpty(String str)
	{
		if(str==null||str.length()==0)
		{
			return false;
		}
		return true;
	}
	
	//数组转成String 
	public String link(int[] os,String interval)
	{
		String result = "";
		for(int i=0;i<os.length;i++)
		{
			if(i !=0)
			{
				result += interval;
			}
			result += os[i] ;
		}
		return result;
	}
	
	//数组转成String 
	public String link(Object[] os,String interval)
	{
		String result = "";
		for(int i=0;i<os.length;i++)
		{
			if(i !=0)
			{
				result += interval;
			}
			result += os[i] ;
		}
		return result;
	}
	
	//列表转成String 
	public String link(List list,String interval)
	{
		String result = "";
		for(int i=0;i<list.size();i++)
		{
			if(i !=0)
			{
				result += interval;
			}
			result += list.get(i) ;
		}
		return result;
	}
	
	
	public String[] distinct(String[] strs)
	{
		Set set = new HashSet();
		for(int i=0;i<strs.length;i++)
		{
			set.add(strs[i]);
		}
		
		return (String[]) set.toArray(new String[set.size()]);
	}
}
