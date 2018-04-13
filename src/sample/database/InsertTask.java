package sample.database;

import java.util.List;

import tool.common.Shift;
import frame.hibernate.DBUtil;

public class InsertTask extends Thread
{
	private static Object m_lock = new Object();
	
	private static Object insert_lock = new Object();

	public static List<String> tables = null;
	
	public String table = null;

	int i = 0;

	public void setI(int no)
	{
		i = no;
	}
	public static boolean isFree()
	{
		synchronized(m_lock){
			
		}
//		System.out.println(".....检测是否为空,空闲表数据："+tables.size());
		return tables.size()>0;
	}
	

	public static void setTables(List<String> ts)
	{
		tables = ts;
	}

	public static String getTable()
	{
		System.out.println("获取当前空闲表");
		if (tables.size() > 0)
		{
			synchronized (m_lock)
			{
				String tableName = tables.get(0);
				tables.remove(0);
				return tableName;
			}
		}
		return "";
	}
	
	public static void addTable(String table)
	{
		synchronized (m_lock)
		{
			System.out.println("添加空闲表" + table);
			tables.add(table);
		}
	}

	@Override
	public void run()
	{
		// String table = "sweet_tree_"
		// + Shift.leftZeroShift(String.valueOf(i), 5);

		// String create = String.format("CREATE TABLE `%s` ("
		// + "`id` bigint(12) NOT NULL AUTO_INCREMENT,"
		// + "`code` varchar(56) DEFAULT NULL,"
		// + "`parent` varchar(56) DEFAULT NULL,"
		// + "`name` varchar(56) DEFAULT NULL," + "PRIMARY KEY (`id`)"
		// + ") ENGINE=InnoDB DEFAULT CHARSET=utf8;", table);
		// String delete =String.format("DROP TABLE IF EXISTS `%s`;", table);
		// try
		// {
		// DBUtil.execute(create, true);
		// } catch (Exception e1)
		// {
		// e1.printStackTrace();
		// }
		String delete = "DELETE FROM " + table;
		try
		{
			DBUtil.execute(delete, true);
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}

		System.out.println("开始插入数据" + i);
		String insert = String.format(
				"insert  into `%s`(`code`,`parent`,`name`) values", table);
		int count = 0;
		for (int j = 0; j < 10000; j++)
		{
			if (count != 0)
				insert += ",";
			insert += String.format(
					"('%s','%s','%s')",
					Shift.leftZeroShift(String.valueOf(i), 5)
							+ Shift.leftZeroShift(String.valueOf(j), 5),
					Shift.leftZeroShift(String.valueOf(i), 5), "tree" + i + j);
			count++;
			//每30次插入一次
			if (count == 30 || j == 9999)
			{
				count = 0;
				try
				{
					// System.out.println(insert);
					DBUtil.execute(insert, true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				insert = String.format(
						"insert  into `%s`(`code`,`parent`,`name`) values",
						table);
			}
		}
		System.out.println("结束插入数据" + i);
		InsertFinishTask.i().addTable(table);

//		try
//		{
//			synchronized(insert_lock){
//				DBUtil.execute(add, true);
//			}
//			System.out.println("完成数据汇总" + i);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		addTable(table);
//		System.out.println("释放表"+table);
//		System.out.println("空闲表数量"+tables.size());
//		try
//		{
//			DBUtil.execute(delete, true);
//			System.out.println("删除数据表" + i);
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}

	}
}
