package sample.database;

import java.util.ArrayList;
import java.util.List;

import frame.hibernate.DBUtil;

import tool.common.Shift;

public class InsertUtils
{
	public static List<String> buildTable(int no, String tableName)
	{
		List tables = new ArrayList();

		for (int i = 0; i < no; i++)
		{
			String table = tableName + "_"
					+ Shift.leftZeroShift(String.valueOf(i), 5);
			String create = String.format("CREATE TABLE `%s` ("
					+ "`id` bigint(12) NOT NULL AUTO_INCREMENT,"
					+ "`code` varchar(56) DEFAULT NULL,"
					+ "`parent` varchar(56) DEFAULT NULL,"
					+ "`name` varchar(56) DEFAULT NULL," + "PRIMARY KEY (`id`)"
					+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;", table);
			String delete = String.format("DROP TABLE IF EXISTS `%s`;", table);
			try
			{
				DBUtil.execute(delete, true);
				DBUtil.execute(create, true);
			} catch (Exception e1)
			{
				e1.printStackTrace();
			}
			tables.add(table);
		}

		return tables;
	}
}
