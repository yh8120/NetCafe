package dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoFactory {

	public static UserDao createUserDao() {
		return new UserDaoImpl(getDataSource());

	}

	public static RoomDao createRoomDao() {
		return new RoomDaoImpl(getDataSource());

	}
	

	public static RoomTypeDao createRoomTypeDao() {
		return new RoomTypeDaoImpl(getDataSource());

	}
	
	public static UserClassDao createUserClassDao() {
		return new UserClassDaoImpl(getDataSource());

	}
	
	public static CustomerDao createCustomerDao() {
		return new CustomerDaoImpl(getDataSource());
		
	}
	
	public static CustomerClassDao createCustomerClassDao() {
		return new CustomerClassDaoImpl(getDataSource());
		
	}
	
	public static CardDao createCardDao() {
		return new CardDaoImpl(getDataSource());

	}
	
	public static SexDao createSexDao() {
		return new SexDaoImpl(getDataSource());

	}

	private static DataSource getDataSource() {
		InitialContext ctx = null;
		DataSource ds = null;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/netcafedb");
		} catch (NamingException e) {
			if (ctx != null) {
				try {
					ctx.close();
				} catch (NamingException e1) {
					throw new RuntimeException(e1);
				}
			}
			throw new RuntimeException(e);
		}
		return ds;
	}

}
