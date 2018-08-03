package tool;

import tool.common.ArrayUtils;
import tool.common.Convert;
import tool.common.DateUtils;
import tool.common.ReplyUtils;
import tool.common.Shift;
import tool.common.StringUtils;
import tool.file.BaseFileUtil;

public class CommonTool
{
	private static CommonTool tool =new CommonTool();
	public static CommonTool getTools()
	{
		return tool;
	}
	/**
	 *数组 相关处理方法
	 */
	public final ArrayUtils ArrayUtils =new ArrayUtils();
	/**
	 *类型转换 相关处理方法
	 */
	public final Convert Convert = new Convert();
	
	public final DateUtils DateUtils = new DateUtils();
	
	public final StringUtils StringUtils = new StringUtils();
	
	public final Shift Shift= new Shift();
	
	public final ReplyUtils ReplyUtils= new ReplyUtils();
	
	public final BaseFileUtil BaseFileUtil = new BaseFileUtil();
}
