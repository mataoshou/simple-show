package sample.database;

import org.hibernate.Session;

import frame.hibernate.DBUtil;
import frame.hibernate.HibernateSessionFactory;

public class Test
{
	public static void main(String [] args) throws Exception
	{
		String sql ="INSERT INTO `sweet_tree`(`code`,`parent`,`name`) SELECT code,parent,name FROM sweet_tree_00049;  INSERT INTO `sweet_tree`(`code`,`parent`,`name`) SELECT code,parent,name FROM sweet_tree_00001;";
		Session dbss = HibernateSessionFactory.getSession();
		try
		{
			dbss.beginTransaction();
			dbss.createSQLQuery(sql).executeUpdate();
			dbss.getTransaction().commit();

		} finally
		{
			if (dbss != null)
				dbss.close();
		}
	}
}
