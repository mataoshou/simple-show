package sample.database.mapper;

import frame.hibernate.AbstractMapper;

public class BeanContent extends AbstractMapper {

	
	
	public static void main(String[] args)
	{
		BeanContent bean = new BeanContent();
		try {
			bean.get(1);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
