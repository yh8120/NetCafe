package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Shop;

public class ShopDaoImpl implements ShopDao {
	private DataSource ds;

	public ShopDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Shop> findAll() throws Exception {
		List<Shop> shopList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM master_shops";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				shopList.add(mapToShop(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return shopList;
	}

	@Override
	public Shop findById(Integer id) throws Exception {
		Shop shop = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM shopes"
					+ " WHERE shop_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				shop = mapToShop(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return shop;
	}

	@Override
	public void insert(Shop shop) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO shopes"
					+ " (shop_id,shop_name,shop_phone_number,shop_address)"
					+ " VALUES (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shop.getShopId(),Types.INTEGER);
			stmt.setString(2, shop.getShopName());
			stmt.setString(3, shop.getShopName());
			stmt.setString(4, shop.getShopName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Shop shop) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE shopes"
					+ " SET"
					+ " shop_name = ?,"
					+ " shop_phone_number = ?,"
					+ " shop_address = ?"
					+ " WHERE shop_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, shop.getShopName());
			stmt.setString(1, shop.getShopPhoneNumber());
			stmt.setString(1, shop.getShopAddress());
			stmt.setObject(1, shop.getShopId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Shop shop) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM shopes"
					+ " Where shop_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shop.getShopId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Shop mapToShop(ResultSet rs) throws Exception {
		Shop shop = new Shop();
		shop.setShopId((Integer) rs.getObject("shop_id"));
		shop.setShopName(rs.getString("shop_name"));
		shop.setShopPhoneNumber(rs.getString("shop_phone_number"));
		shop.setShopAddress(rs.getString("shop_address"));

		return shop;

	}

}
