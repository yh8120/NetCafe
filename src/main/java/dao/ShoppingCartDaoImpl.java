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
					+ " FROM shopping_carts";
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
					+ " WHERE cart_id=?";
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
					+ " (room_id,"
					+ " sales_time,"
					+ " product_id,"
					+ " product_name,"
					+ " product_unit,"
					+ " total_price,"
					+ " discount,"
					+ " tax_type,"
					+ " tax_name,"
					+ " inner_tax)"
					+ " VALUES (?,NOW(),?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shoppingCart.getRoomId(), Types.INTEGER);
			stmt.setObject(2, shoppingCart.getProductId(), Types.INTEGER);
			stmt.setString(3, shoppingCart.getProductName());
			stmt.setObject(4, shoppingCart.getProductUnit(), Types.INTEGER);
			stmt.setObject(5, shoppingCart.getTotalPrice(), Types.INTEGER);
			stmt.setObject(6, shoppingCart.getDiscount(), Types.INTEGER);
			stmt.setObject(7, shoppingCart.getTaxType(), Types.INTEGER);
			stmt.setString(8, shoppingCart.getTaxName());
			stmt.setObject(9, shoppingCart.getInnerTax(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
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
		shoppingCart.setShoppingCartId((Integer) rs.getObject("cart_id"));
		shoppingCart.setSalesTime(rs.getTimestamp("sales_time"));
		shoppingCart.setRoomId((Integer) rs.getObject("room_id"));
		shoppingCart.setProductId((Integer) rs.getObject("product_id"));
		shoppingCart.setProductName(rs.getString("product_name"));
		shoppingCart.setProductUnit((Integer) rs.getObject("product_unit"));
		shoppingCart.setTotalPrice((Integer)rs.getObject("total_price"));
		shoppingCart.setDiscount((Integer) rs.getObject("discount"));
		shoppingCart.setTaxType((Integer) rs.getObject("tax_type"));
		shoppingCart.setTaxName(rs.getString("tax_name"));
		shoppingCart.setInnerTax((Integer) rs.getObject("inner_tax"));

		return shoppingCart;
	}

}
