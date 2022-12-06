package dao;

import java.util.List;

import domain.Product;

public interface ProductDao {
	List<Product> findAll() throws Exception;
	List<Product> findShoppingList() throws Exception;
	Product findById(Integer productId) throws Exception;
	void insert (Product product) throws Exception;
	void update (Product product) throws Exception;
	void delete (Product product) throws Exception;
}
