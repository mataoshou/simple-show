package sample.database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import frame.hibernate.DBUtil;
import frame.hibernate.HibernateSessionFactory;
//汇总表格数据，插入到总表中
public class InsertFinishTask extends Thread
{
	Object m_lock = new Object();

	private static InsertFinishTask one = null;

	public static InsertFinishTask i()
	{
		if (one == null)
		{
			one = new InsertFinishTask();
		}
		return one;
	}

	List<String> tables = new ArrayList();

	public void addTable(String tableName)
	{
		synchronized (m_lock)
		{
			System.out.println("加入汇总队列"+tableName);
			tables.add(tableName);
		}
	}

	@Override
	public void run()
	{
		String insert ="";
		while (true)
		{
			try
			{
				insert = "";
				if (tables.size() > 0)
				{
					List<String> tmp = null;
					System.out.println("开始处理汇总表");
					synchronized (m_lock)
					{
						tmp = tables;
						tables = new ArrayList();
					}
					Session dbss = HibernateSessionFactory.getSession();
					try
					{
						dbss.beginTransaction();
						for (String table : tmp)
						{
							insert = String
									.format("INSERT INTO `sweet_tree`(`code`,`parent`,`name`) SELECT code,parent,name FROM %s;\r\n",
											table);
							dbss.createSQLQuery(insert).executeUpdate();
						}
						dbss.getTransaction().commit();
					} finally
					{
						if (dbss != null)
							dbss.close();
					}
					
//					System.out.println(insert);
//					DBUtil.execute(insert, true);
					System.out.println("释放分表"+tmp.size()+"个");
					for(String table : tmp)
					{
						InsertTask.addTable(table);
					}
					tables.clear();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
