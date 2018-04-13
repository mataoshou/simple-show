package tool.sql;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;

import tool.common.SystemPath;
import tool.file.BaseFileUtil;
import frame.hibernate.DBUtil;
import frame.hibernate.HibernateSessionFactory;
import frame.mtfilter.MtTool;

public class BuildMapper
{

	File root = null;// 文件根目录
	List<String> tables = new ArrayList();// 数据库表列表
	String db = "";// 数据库名称
	Logger logger = Logger.getLogger(getClass());

	// 初始化
	private void init(File r, Configuration config)
	{
		String url = config.getProperty("hibernate.connection.url");
		db = url.substring(url.lastIndexOf("/") + 1);// 获取数据库名称
		root = r;// 根目录
	}

	private void begin()
	{
		try
		{
			getTables();
			// 遍历表，生成映射文件
			for (String s : tables)
			{
				logger.debug("生成数据库表" + s + "映射文件");
				buildMapperFile(s);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void buildMapperFile(String table) throws Exception
	{
		// 获取calss名称
		char first = Character.toUpperCase(table.charAt(0));
		StringBuffer sb = new StringBuffer(table);
		sb.setCharAt(0, first);
		String className = sb.toString();
		logger.debug("开始生成类" + className);
		String c_file_name = className + ".java";
		File class_file = new File(root, c_file_name);

		BaseFileUtil.delete(class_file);

		List<Column> columns = getColumns(table);
		buildFile(className, class_file, columns);
	}

	private void buildFile(String className, File class_file,
			List<Column> columns) throws IOException
	{
		FileOutputStream out = new FileOutputStream(class_file);
		int tab_no = 0;
		String tab = "	";

		String header = "";
		String content = "";
		// 添加头部
		header += getContent(tab_no, tab, "package base.db;");
		header += getContent(tab_no, tab, "");

		for (Column c : columns)
		{
			String imp = addImport(c);
			if (imp != null && imp.trim().length() > 0)
			{
				if (header.indexOf(imp) < 0)
				{
					header += getContent(tab_no, tab, imp);
				}
			}
		}
		header += getContent(tab_no, tab, "public class " + className);
		header += getContent(tab_no, tab, "");
		header += getContent(tab_no, tab, "{");
		// 生成属性
		tab_no++;
		for (Column c : columns)
		{
			content += getContent(
					tab_no,
					tab,
					String.format("private %s %s;", c.getJava_type(),
							c.getName()));
		}
		// 生成get和set方法
		for (Column c : columns)
		{
			content += buildGet(c, tab_no, tab);
			content += buildSet(c, tab_no, tab);
		}

		tab_no--;
		content += getContent(tab_no, tab, "}");
		out.write((header + content).getBytes());

		out.close();

	}

	private String addImport(Column c)
	{
		String[] java_type =
		{ "Timestamp" };
		String[] java_import =
		{ "java.sql.Timestamp" };
		for (int i = 0; i < java_type.length; i++)
		{
			if (java_type[i].equals(c.getJava_type()))
			{
				return String.format("import %s;", java_import[i]);
			}
		}
		return "";
	}

	private String buildGet(Column c, int tab_no, String tab)
	{
		String content = getContent(tab_no, tab, "");
		content += getContent(
				tab_no,
				tab,
				String.format("public %s %s(){", c.getJava_type(),
						MtTool.getGetMethodName(c.getName())));
		tab_no++;
		content += getContent(tab_no, tab,
				String.format("return this.%s;", c.getName()));
		tab_no--;
		content += getContent(tab_no, tab, String.format("}"));

		return content;

	}

	private String buildSet(Column c, int tab_no, String tab)
	{
		String content = getContent(tab_no, tab, "");
		content += getContent(tab_no, tab, String.format(
				"public void %s(%s %s){", MtTool.getSetMethodName(c.getName()),
				c.getJava_type(), c.getName()));
		tab_no++;
		content += getContent(tab_no, tab,
				String.format("this.%s = %s;", c.getName(), c.getName()));
		tab_no--;
		content += getContent(tab_no, tab, String.format("}"));

		return content;
	}

	private String getContent(int tab_no, String tab, String content)
	{
		String tabs = "";
		for (int i = 0; i < tab_no; i++)
		{
			tabs += tab;
		}
		String ending = "\r\n";
		return tabs + content + ending;
	}

	// 获取java对应类型
	private String getType(String type)
	{
		String[] db_type =
		{ "varchar", "bigint", "int", "tinyint", "datetime", "date", "long",
				"double", "float" };
		String[] java_type =
		{ "String", "int", "int", "boolean", "Timestamp", "Timestamp", "long",
				"double", "float" };

		for (int i = 0; i < db_type.length; i++)
		{
			if (type.equals(db_type[i]))
			{
				return java_type[i];
			}
		}
		return "";
	}

	private List<Column> getColumns(String table) throws Exception
	{
		String column_sql = String
				.format("SELECT column_name ,data_type ,column_comment,character_maximum_length FROM information_schema.columns WHERE table_schema = '%s'  AND table_name = '%s'",
						db, table);
		List<Object[]> rows = DBUtil.list(column_sql, true);
		List<Column> columns = new ArrayList();
		for (Object[] row : rows)
		{
			Column c = new Column();
			c.setName(row[0].toString());
			c.setType(row[1].toString());
			c.setMark(row[2].toString());
			if(row[3]!=null)
			{
				c.setLength(Integer.valueOf(row[3].toString()));
			}
			c.setJava_type(getType(c.getType()));
			columns.add(c);
		}
		return columns;
	}

	public void getTables() throws Exception
	{
		// /获取所有的数据库表
		String sql = "SELECT table_name " + "FROM information_schema.tables "
				+ "WHERE  table_schema = '##1'".replace("##1", db);// 所在数据库

		tables = DBUtil.list(sql, true);

	}
	
	public void buildTable() throws Exception
	{
		getTables();
		for(String s :tables)
		{
			List<Column> columns = getColumns(s);
			SqlUtils.buildTable(s, columns);
		}
	}

	public static void main(String[] args) throws Exception
	{
		BuildMapper m = new BuildMapper();
		SystemPath sys = new SystemPath();
		File root = new File(sys.getRoot(), "src\\base\\db");// 生成到db中
		m.init(root, HibernateSessionFactory.getConfiguration());
		m.buildTable();
	}

}
