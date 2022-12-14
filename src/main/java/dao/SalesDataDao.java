package dao;

import java.util.List;

import domain.SalesData;
import domain.ShoppingCart;

public interface SalesDataDao {
	List<SalesData> findAll() throws Exception;
	List<SalesData> findByReceiptId(Integer receiptId) throws Exception;
	SalesData findById(Integer salecDataId) throws Exception;
	void insert (ShoppingCart shoppingCart) throws Exception;
	void update (SalesData salecData) throws Exception;
	void delete (SalesData salecData) throws Exception;
}
