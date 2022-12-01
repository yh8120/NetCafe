package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.ReceiptData;

public class ReceiptDataDaoImpl implements ReceiptDataDao {
	private DataSource ds;

	public ReceiptDataDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<ReceiptData> findAll() throws Exception {
		List<ReceiptData> salesDataList = new ArrayList<>();

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
	public ReceiptData findById(Integer salesDataId) throws Exception {
		ReceiptData salesData = null;
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
	public Integer insert(ReceiptData receiptData) throws Exception {
		ResultSet resultSet = null;
		Integer autoIncrementKey = null;
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO sales_data"
					+ " (receipt_id,shop_id,user_id,customer_id,sales_date,sum_price,start_time,time_spent)"
					+ " VALUES (?,?,?,?,NOW(),?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, receiptData.getReceiptId(), Types.INTEGER);
			stmt.setObject(2, receiptData.getShopId(), Types.INTEGER);
			stmt.setObject(3, receiptData.getUserId(), Types.INTEGER);
			stmt.setObject(4, receiptData.getCustomerId(), Types.INTEGER);
			stmt.setObject(5, receiptData.getSumPrice(), Types.INTEGER);
			stmt.setTimestamp(6, (Timestamp) receiptData.getStartTime());
			stmt.setObject(7, receiptData.getTimeSpent(), Types.INTEGER);
			stmt.executeUpdate();

			resultSet = stmt.getGeneratedKeys();

		} catch (Exception e) {
			throw e;
		}

		if (resultSet.next()) {
			autoIncrementKey = resultSet.getInt(1);
		}
		return autoIncrementKey;

	}

	@Override
	public void update(ReceiptData salesData) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "UPDATE sales_data"
		//					+ " (shop_id,user_id,customer_id,sales_date,sum_price,start_time,time_spent)"
		//					+ " WHERE receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, salesData.getShopId(), Types.INTEGER);
		//			stmt.setObject(2, salesData.getUserId(), Types.INTEGER);
		//			stmt.setObject(3, salesData.getCustomerId(), Types.INTEGER);
		//			stmt.setObject(4, salesData.getSales(), Types.INTEGER);
		//			stmt.setObject(5, salesData.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	@Override
	public void delete(ReceiptData salesData) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "DELETE FROM sales_data"
		//					+ " Where receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, salesData.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	private ReceiptData mapToSalesData(ResultSet rs) throws Exception {
		ReceiptData receiptData = new ReceiptData();
		receiptData.setReceiptId((Integer) rs.getObject("receipt_id"));
		receiptData.setShopId((Integer) rs.getObject("store_id"));
		receiptData.setUserId((Integer) rs.getObject("user_id"));
		receiptData.setCustomerId((Integer) rs.getObject("customer_id"));
		receiptData.setSalesDate(rs.getTimestamp("sales_date"));
		receiptData.setSumPrice((Integer) rs.getObject("sum_price"));
		receiptData.setStartTime(rs.getTimestamp("start_time"));
		receiptData.setTimeSpent((Long) rs.getObject("time_spent"));
		SalesDataDao salesDataDao = DaoFactory.createSalesDataDao();
		receiptData.setSalesData(salesDataDao.findByReceiptId(receiptData.getReceiptId()));

		return receiptData;
	}

}
