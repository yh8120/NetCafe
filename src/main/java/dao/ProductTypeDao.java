package dao;

import java.util.List;

import domain.ProductType;

public interface ProductTypeDao {
	List<ProductType> findAll() throws Exception;
	ProductType findById(Integer id) throws Exception;
	void insert (ProductType productType) throws Exception;
	void update (ProductType productType) throws Exception;
	void delete (ProductType productType) throws Exception;
}
