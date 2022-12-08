package dao;

import java.util.List;

import domain.ShoppingCart;

public interface ShoppingCartDao {
	List<ShoppingCart> findAll() throws Exception;
	List<ShoppingCart> findByRoomId(Integer roomId) throws Exception;
	ShoppingCart findById(Integer shoppingCartId) throws Exception;
	void insert (ShoppingCart shoppingCart) throws Exception;
	void update (ShoppingCart shoppingCart) throws Exception;
	void delete (ShoppingCart shoppingCart) throws Exception;
}
