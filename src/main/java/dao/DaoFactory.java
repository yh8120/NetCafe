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
	
	public static IdCardDao createIdCardDao() {
		return new IdCardDaoImpl(getDataSource());

	}
	
	public static SexDao createSexDao() {
		return new SexDaoImpl(getDataSource());

	}
	
	public static ReceiptDataDao createRecieptDataDao() {
		return new ReceiptDataDaoImpl(getDataSource());
	}
	
	public static SalesDataDao createSalesDataDao() {
		return new SalesDataDaoImpl(getDataSource());
	}
	
	public static ProductDao createProductDao() {
		return new ProductDaoImpl(getDataSource());
	}
	
	public static ShopDao createShopDao() {
		return new ShopDaoImpl(getDataSource());
	}
	
	public static PricePlanDao createPricePlanDao() {
		return new PricePlanDaoImpl(getDataSource());
	}
	
	public static ShoppingCartDao createShoppingCartDao() {
		return new ShoppingCartDaoImpl(getDataSource());
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
