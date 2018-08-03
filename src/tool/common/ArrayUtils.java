package tool.common;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils
{
	
	public static void main(String[] args) throws Exception
	{
		ArrayUtils arr = new ArrayUtils();
		
		String[] strs = (String[]) arr.subArray(new String[]{"aa","bb","cc","dd"}, 1, 2);
		System.out.println(arr.subArray(new String[]{"aa","bb","cc","dd"}, 1, 2).length);
		System.out.println(arr.subArray(new String[]{"aa","bb","cc","dd"}, 1, 2)[0]);
	}
	/**
	 *对象数组截取 
	 * @throws Exception 
	 */
	public  Object[] subArray(Object[] strs, int begin ,int end) throws Exception 
	{
		if(begin>=strs.length)throw new Exception("起始值超出数组长度");
		
		List result = new ArrayList();
		for(int i=0;i<end-begin;i++)
		{
			result.add(strs[begin+i]);
		}
		return result.toArray();
	}
	
	public int getMaxIndex(int[] is)
	{		
		return getMaxIndex(is,0,is.length,new int[0]);
	}
	
	public int getMaxIndex(int[] is,int begin ,int end,int[] ingore)
	{
		int no =-1;
		int max =-1;
		
		for(int i=begin;i<end;i++)
		{
			if(is[i]>max)
			{
				no = i;
				max =is[i];
			}
		}
		
		return no;
	}
	
	public int getMinIndex(int[] is)
	{
		return getMinIndex(is,0,is.length ,new int[0]);
	}
	
	
	public int getMinIndex(int[] is,int begin ,int end,int[] ingore)
	{
		int no =-1;
		int min =Integer.MAX_VALUE;
		
		for(int i=begin;i<end;i++)
		{
			if(ingore.length>0)
			{
				if(contain(is[i],ingore))continue;
			}
			if(is[i]<min)
			{
				no = i;
				min =is[i];
			}
		}
		
		return no;
	}
	
	public boolean contain(int no,int[] arr)
	{
		for(int j=0;j<arr.length;j++)
		{
			if(no==arr[j])
			{
				return true;
			}
		}
		
		return false;
	}
	
	public int getMin(int[] is,int begin ,int end,int[] ingore)
	{
		int index = getMinIndex(is,begin,end,ingore);
		if(index ==-1) return Integer.MAX_VALUE;
		return is[index];
	}
	
	public int getMax(int[] is,int begin ,int end,int[] ingore)
	{
		int index = getMaxIndex(is,begin,end,ingore);
		if(index ==-1) return Integer.MAX_VALUE;
		return is[index];
	}
}
