package tool;

import tool.common.ArrayUtils;
import tool.common.Convert;

public class CommonToolUtil
{
	private static CommonToolUtil tool =new CommonToolUtil();
	public static CommonToolUtil getTools()
	{
		return tool;
	}
	/**
	 *数组 相关处理方法
	 */
	public ArrayUtils arrayUtils =new ArrayUtils();
	/**
	 *类型转换 相关处理方法
	 */
	public Convert convert = new Convert();
	
}
