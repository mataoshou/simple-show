package frame.hibernate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import frame.mtfilter.MtTool;

import tool.common.SystemPath;
import tool.file.BaseFileUtil;

public class BuildMapper
{

	File root = null;// 文件根目录
	List<String> tables = new ArrayList();// 数据库表列表
	String db = "";// 数据库名称
	Logger logger = Logger.getLogger(getClass());

	// 初始化
	private void init(File r)
	{
		String url = HibernateSessionFactory.getConfiguration().getProperty(
				"hibernate.connection.url");

		db = url.substring(url.lastIndexOf("/") + 1);// 获取数据库名称
		root = r;// 根目录

		try
		{
			tablesList();// 获取数据库表
			// 遍历表，生成映射文件
			for (String s : tables)
			{
				logger.debug("生成数据库表" + s + "映射文件");
				buildMapper(s);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildMapper(String table) throws Exception
	{
		// 获取calss名称
		char first = Character.toUpperCase(table.charAt(0));
		StringBuffer sb = new StringBuffer(table);
		sb.setCharAt(0, first);
		String className = sb.toString();
		logger.debug("开始生成类" + className);
		String c_file_name = className + ".java";
		File class_file = new File(root, c_file_name);

		if (class_file.exists())
		{
			BaseFileUtil.delete(class_file);
		}
		
		List<Column> columns = getColumns(table);
		createFile(className,class_file,columns);
	}
	
	private void createFile(String className,File class_file,List<Column> columns) throws IOException
	{
		FileOutputStream out = new FileOutputStream(class_file);
		int tab_no = 0;
		String tab = "	";
		
		String content ="";
		
		content += getContent(tab_no,tab,"package base.db;");
		
		content += getContent(tab_no,tab,"public class "+className +" extends AbstractMapper");
		
		content += getContent(tab_no,tab,"{");
		
		tab_no++;
		for(Column c : columns)
		{
			content += getContent(tab_no,tab,String.format("private %s %s;",c.getJava_type(),c.getName()));
		}
		
		for(Column c : columns)
		{
			content += buildGet(c,tab_no,tab);
			content += buildSet(c,tab_no,tab);
		}
		
		tab_no--;
		content += getContent(tab_no,tab,"}");
		out.write(content.getBytes());
		
		out.close();
		
		
	}
	
	
	private String getContent(int tab_no,String tab,String content)
	{
		String tabs="";
		for(int i=0;i<tab_no;i++)
		{
			tabs+=tab;
		}
		return tabs+content+"\r\n";
	}
	
	private String getType(String type)
	{
		if(type.equals("varchar")){
			return "String";
		}
		
		if(type.equals("bigint")||type.equals("int"))
		{
			return "int";
		}
		
		if(type.equals("tinyint"))
		{
			return "boolean";
		}
		if(type.equals("datetime"))
		{
			return "Timestamp";
		}
		
		if(type.equals("double"))
		{
			return "double";
		}
		
		if(type.equals("float"))
		{
			return "float";
		}
		return "";
	}
	
	private List<Column> getColumns(String table) throws Exception
	{
		String column_sql = String
				.format("SELECT column_name ,data_type ,column_comment FROM information_schema.columns WHERE table_schema = '%s'  AND table_name = '%s'",
						db, table);
		List<Object[]> rows = DBUtil.list(column_sql, true);
		List<Column> columns = new ArrayList();
		for (Object[] row : rows)
		{
			Column c = new Column();
			c.setName(row[0].toString());
			c.setType(row[1].toString());
			c.setMark(row[2].toString());
			c.setJava_type(getType(c.getType()));
			columns.add(c);
		}
		return columns;
	}

	private String buildGet(Column c,int tab_no,String tab)
	{
		String content="";
		content += getContent(tab_no,tab,String.format("public %s %s(){", c.getJava_type(), MtTool.getGetMethodName(c.getName())));
		tab_no++;
		content += getContent(tab_no,tab,String.format("return this.%s;", c.getName()));
		tab_no--;
		content += getContent(tab_no,tab,String.format("}"));
		
		return content;
		
	}

	private String buildSet(Column c,int tab_no,String tab)
	{
		String content="";
		content += getContent(tab_no,tab,String.format("public void %s(%s %s){", 
				MtTool.getSetMethodName(c.getName()),c.getJava_type(),c.getName()));
		tab_no++;
		content += getContent(tab_no,tab,String.format("this.%s = %s;", c.getName(),c.getName()));
		tab_no--;
		content += getContent(tab_no,tab,String.format("}"));
		
		return content;
	}

	public void tablesList() throws Exception
	{
		// /获取所有的数据库表
		String sql = "SELECT table_name " + "FROM information_schema.tables "
				+ "WHERE  table_schema = '##1'".replace("##1", db);// 所在数据库

		tables = DBUtil.list(sql, true);

	}

	public static void main(String[] args)
	{
		BuildMapper m = new BuildMapper();
		SystemPath sys = new SystemPath();
		File root = new File(sys.getRoot(), "src\\base\\db");// 生成到db中
		m.init(root);
	}

}
