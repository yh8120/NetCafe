package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.SalesData;
import domain.ShoppingCart;

public class SalesDataDaoImpl implements SalesDataDao {
	private DataSource ds;

	public SalesDataDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<SalesData> findAll() throws Exception {
		List<SalesData> salesDataList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salesDataList.add(mapToSalesData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return salesDataList;
	}

	@Override
	public List<SalesData> findByReceiptId(Integer receiptId) throws Exception {
		List<SalesData> salesDataList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id"
					+ " WHERE receipt_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, receiptId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				salesDataList.add(mapToSalesData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return salesDataList;
	}

	@Override
	public SalesData findById(Integer salesDataId) throws Exception {
		SalesData salesData = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_datas"
					+ " JOIN master_products ON sales_datas.product_id = master_product.product_id"
					+ " WHERE sales_data_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesDataId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				salesData = mapToSalesData(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return salesData;
	}

	@Override
	public void insert(ShoppingCart shoppingCart) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO sales_data"
					+ " (room_id,"
					+ " sales_time,"
					+ " product_id,"
					+ " product_unit,"
					+ " total_price,"
					+ " discount,"
					+ " inner_tax)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, shoppingCart.getRoomId(), Types.INTEGER);
			stmt.setObject(2, shoppingCart.getSalesTime(),Types.TIMESTAMP);
			stmt.setObject(3, shoppingCart.getProductId(), Types.INTEGER);
			stmt.setObject(4, shoppingCart.getProductUnit(), Types.INTEGER);
			stmt.setObject(5, shoppingCart.getTotalPrice(), Types.INTEGER);
			stmt.setObject(6, shoppingCart.getDiscount(), Types.INTEGER);
			stmt.setObject(7, shoppingCart.getInnerTax(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	@Override
	public void update(SalesData salesData) throws Exception {
		
	}

	@Override
	public void delete(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM sales_datas"
					+ " Where sales_data_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getSalesDataId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private SalesData mapToSalesData(ResultSet rs) throws Exception {
		SalesData salesData = new SalesData();
		salesData.setSalesDataId((Integer) rs.getObject("cart_id"));
		salesData.setSalesTime(rs.getTimestamp("sales_time"));
		salesData.setRoomId((Integer) rs.getObject("room_id"));
		salesData.setProductId((Integer) rs.getObject("product_id"));
		salesData.setProductUnit((Integer) rs.getObject("product_unit"));
		salesData.setTotalPrice((Integer)rs.getObject("total_price"));
		salesData.setDiscount((Integer) rs.getObject("discount"));
		salesData.setInnerTax((Integer) rs.getObject("inner_tax"));

		return salesData;
	}

}
