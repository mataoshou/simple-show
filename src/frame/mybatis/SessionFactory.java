package frame.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SessionFactory 
{
	private static SqlSessionFactory factory=null;
	
	public static SqlSessionFactory getFactory()
	{
		if(factory==null)
		{
			String resource = "config/mybatis-config.xml";
			try
			{
				initFactory(resource);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return factory;
	}
	
	private static SqlSessionFactory initFactory(String resource) throws IOException
	{
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
		return factory;
	}
}
