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
}
