package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		Integer autoIncrementKey = null;
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO receipt_data"
					+ "(shop_id,"
					+ " user_id,"
					+ " printed_time,"
					+ " sum_price,"
					+ " sum_tax,"
					+ " payment,"
					+ " change_money)"
					+ " VALUES (?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
			stmt.setObject(1, receiptData.getShopId(), Types.INTEGER);
			stmt.setObject(2, receiptData.getUserId(), Types.INTEGER);
			stmt.setObject(3, receiptData.getPrintedTime(),Types.TIMESTAMP);
			stmt.setObject(4, receiptData.getSumPrice(), Types.INTEGER);
			stmt.setObject(5, receiptData.getSumTax(), Types.INTEGER);
			stmt.setObject(6, receiptData.getPayment(), Types.INTEGER);
			stmt.setObject(7, receiptData.getChangeMoney(), Types.INTEGER);
			stmt.executeUpdate();
			
			ResultSet res = stmt.getGeneratedKeys();

	         if(res.next()){
	            autoIncrementKey = res.getInt(1);
	            System.out.println(autoIncrementKey);
	         }

		} catch (Exception e) {
			throw e;
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
		receiptData.setShopId((Integer) rs.getObject("shop_id"));
		receiptData.setShopName(rs.getString("shop_name"));
		receiptData.setShopPhoneNumber(rs.getString("shop_phone_number"));
		receiptData.setShopAddress(rs.getString("shop_address"));
		receiptData.setUserId((Integer) rs.getObject("user_id"));
		receiptData.setPrintedTime(rs.getTimestamp("printed_time"));
		receiptData.setSumPrice((Integer) rs.getObject("sum_price"));
		receiptData.setSumTax((Integer)rs.getObject("sum_tax"));
		receiptData.setPayment((Integer)rs.getObject("payment"));
		receiptData.setChangeMoney((Integer)rs.getObject("change_money"));

		return receiptData;
	}

}
