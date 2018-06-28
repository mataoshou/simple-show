package tool.common;

public class ArrayUtils
{
	public String[] subArray(String [] strs, int begin ,int end) throws Exception
	{
		if(begin>=strs.length)throw new Exception("起始值超出数组长度");
		
		String[] result =new String[end-begin+1];
		for(int i=0;i<result.length;i++)
		{
			result[i] = strs[begin+i];
		}
		return result;
	}
	
	public int getMaxIndex(int[] is)
	{
		int no =-1;
		int max =-1;
		
		for(int i=0;i<is.length;i++)
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
		int no =-1;
		int min =Integer.MAX_VALUE;
		
		for(int i=0;i<is.length;i++)
		{
			if(is[i]<min)
			{
				no = i;
				min =is[i];
			}
		}
		
		return no;
	}
}
