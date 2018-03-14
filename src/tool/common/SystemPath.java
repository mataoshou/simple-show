package tool.common;

import java.io.File;

public class SystemPath
{
	/**
	 *获取项目根目录 
	 */
	public String getRoot()
	{
		return System.getProperty("user.dir");
	}
	/**
	 *获取类所在目录 
	 */
	public String getClassPath(Class c)
	{
		return c.getResource("").getPath();
	}
	/**
	 *获取classes所在目录 
	 */
	public String getBinPath()
	{
		return this.getClass().getResource("/").getPath();
	}
	
	public static void main(String[] args)
	{
		SystemPath path =new SystemPath();
		System.out.println( path.getRoot());
		System.out.println( path.getClassPath(SystemPath.class));
		System.out.println( path.getBinPath());
	}
}
