package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.SalesData;

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
					+ " FROM sales_data";
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
	public SalesData findById(Integer salesDataId) throws Exception {
		SalesData salesData = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_data"
					+ " WHERE receipt_id=?";
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
	public void insert(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO sales_data"
					+ " (reciept_id,store_id,user_id,customer_id,sales)"
					+ " VALUES (?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getReceiptId(), Types.INTEGER);
			stmt.setObject(2, salesData.getStoreId(), Types.INTEGER);
			stmt.setObject(3, salesData.getUserId(), Types.INTEGER);
			stmt.setObject(4, salesData.getCustomerId(), Types.INTEGER);
			stmt.setObject(5, salesData.getSales(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE sales_data"
					+ " (store_id,user_id,customer_id,sales)"
					+ " WHERE reciept_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getStoreId(), Types.INTEGER);
			stmt.setObject(2, salesData.getUserId(), Types.INTEGER);
			stmt.setObject(3, salesData.getCustomerId(), Types.INTEGER);
			stmt.setObject(4, salesData.getSales(), Types.INTEGER);
			stmt.setObject(5, salesData.getReceiptId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}


	@Override
	public void delete(SalesData salesData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM sales_data"
					+ " Where reciept_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, salesData.getReceiptId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private SalesData mapToSalesData(ResultSet rs) throws Exception {
		SalesData salesData = new SalesData();
		salesData.setReceiptId((Integer) rs.getObject("receipt_id"));
		salesData.setStoreId((Integer) rs.getObject("store_id"));
		salesData.setUserId((Integer) rs.getObject("user_id"));
		salesData.setCustomerId((Integer) rs.getObject("customer_id"));
		salesData.setSales((Integer) rs.getObject("sales"));
		return salesData;
	}

}
