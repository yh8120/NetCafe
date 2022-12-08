package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
	private DataSource ds;

	public ShoppingCartDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<ShoppingCart> findAll() throws Exception {
		List<ShoppingCart> shoppingCartList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM shopping_carts"
					+ " JOIN master_products ON shopping_carts.product_id = master_product.product_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				shoppingCartList.add(mapToShoppingCart(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return shoppingCartList;
	}

	@Override
	public List<ShoppingCart> findByRoomId(Integer roomId) throws Exception {
		List<ShoppingCart> shoppingCartList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM shopping_carts"
					+ " JOIN master_products ON shopping_carts.product_id = master_product.product_id"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				shoppingCartList.add(mapToShoppingCart(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return shoppingCartList;
	}

	@Override
	public ShoppingCart findById(Integer shoppingCartId) throws Exception {
		ShoppingCart shoppingCart = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM shopping_carts"
					+ " JOIN master_products ON shopping_carts.product_id = master_product.product_id"
					+ " WHERE sales_data_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shoppingCartId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				shoppingCart = mapToShoppingCart(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return shoppingCart;
	}

	@Override
	public void insert(ShoppingCart shoppingCart) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO shopping_carts"
					+ " (room_id,product_id,product_unit,total_price,discount)"
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shoppingCart.getRoomId(), Types.INTEGER);
			stmt.setObject(2, shoppingCart.getProductId(), Types.INTEGER);
			stmt.setObject(3, shoppingCart.getProductUnit(), Types.INTEGER);
			stmt.setObject(3, shoppingCart.getTotalPrice(), Types.INTEGER);
			stmt.setObject(3, shoppingCart.getDiscount(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(ShoppingCart shoppingCart) throws Exception {
		
	}

	@Override
	public void delete(ShoppingCart shoppingCart) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM shopping_carts"
					+ " Where room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shoppingCart.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private ShoppingCart mapToShoppingCart(ResultSet rs) throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartId((Integer) rs.getObject("sales_data_id"));
		shoppingCart.setRoomId((Integer) rs.getObject("room_id"));
		shoppingCart.setProductId((Integer) rs.getObject("product_id"));
		shoppingCart.setProductName(rs.getString("product_name"));
		shoppingCart.setProductPrice((Integer) rs.getObject("product_price"));
		shoppingCart.setProductUnit((Integer) rs.getObject("number_product"));
		shoppingCart.setDiscount((Integer) rs.getObject("discount"));

		return shoppingCart;
	}

}
