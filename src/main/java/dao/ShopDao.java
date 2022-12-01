package dao;

import java.util.List;

import domain.Shop;

public interface ShopDao {
	List<Shop> findAll() throws Exception;
	Shop findById(Integer id) throws Exception;
	void insert (Shop shop) throws Exception;
	void update (Shop shop) throws Exception;
	void delete (Shop shop) throws Exception;
}
