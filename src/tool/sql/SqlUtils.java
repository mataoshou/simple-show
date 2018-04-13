package tool.sql;

import java.util.List;

public class SqlUtils
{
	public static void buildTable(String table_name, List<Column> cols)
	{
		String sql = "CREATE TABLE `%s` (%s) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		String columns = "";
		for (Column c : cols)
		{
			if(columns.length()>0)columns+=",";
			columns += String.format(" `%s` %s(%s) NOT NULL",
					c.getName(), c.getType(), c.getLength());
		}
		String create = String.format(sql, table_name,columns);
		System.out.println(create);
	}
}
