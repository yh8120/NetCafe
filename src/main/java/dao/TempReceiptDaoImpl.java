package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.TempReceipt;

public class TempReceiptDaoImpl implements TempReceiptDao {
	private DataSource ds;

	public TempReceiptDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<TempReceipt> findAll() throws Exception {
		List<TempReceipt> tempReceiptList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_data";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tempReceiptList.add(mapToTempReceipt(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return tempReceiptList;
	}
	
	@Override
	public List<TempReceipt> findbyRoomId(Integer roomId) throws Exception {
		List<TempReceipt> tempReceiptList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM temp_receipt"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				tempReceiptList.add(mapToTempReceipt(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return tempReceiptList;
	}

	@Override
	public TempReceipt findById(Integer roomId) throws Exception {
		TempReceipt tempReceipt = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM temp_receipt"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				tempReceipt = mapToTempReceipt(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return tempReceipt;
	}

	@Override
	public Integer insert(TempReceipt tempReceipt) throws Exception {
		Integer autoIncrementKey = null;
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO temp_receipt"
					+ "(room_id,"
					+ " start_time,"
					+ " checkout_time,"
					+ " stayTime,"
					+ " customer_id,"
					+ " customer_name,"
					+ " sum_price,"
					+ " sum_tax,"
					+ " sum_discount,"
					+ " room_price,"
					+ " room_tax,"
					+ " payment,"
					+ " changeMoney"
					+ " VALUES (?,?,NOW(),?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, tempReceipt.getRoomId(), Types.INTEGER);
			stmt.setObject(2, tempReceipt.getStartTime(),Types.TIMESTAMP);
			stmt.setObject(3, tempReceipt.getStayTime(), Types.BIGINT);
			stmt.setObject(4, tempReceipt.getCustomerId(), Types.INTEGER);
			stmt.setString(5, tempReceipt.getCustomerName());
			stmt.setObject(6, tempReceipt.getSumPrice(), Types.INTEGER);
			stmt.setObject(7, tempReceipt.getSumTax(), Types.INTEGER);
			stmt.setObject(8, tempReceipt.getSumDiscount(), Types.INTEGER);
			stmt.setObject(9, tempReceipt.getRoomPrice(), Types.INTEGER);
			stmt.setObject(10, tempReceipt.getRoomTax(), Types.INTEGER);
			stmt.setObject(11, tempReceipt.getPayment(), Types.INTEGER);
			stmt.setObject(12, tempReceipt.getChangeMoney(), Types.INTEGER);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		}

		return autoIncrementKey;

	}

	@Override
	public void update(TempReceipt tempReceipt) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "UPDATE sales_data"
		//					+ " (shop_id,user_id,customer_id,sales_date,sum_price,start_time,time_spent)"
		//					+ " WHERE receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, tempReceipt.getShopId(), Types.INTEGER);
		//			stmt.setObject(2, tempReceipt.getUserId(), Types.INTEGER);
		//			stmt.setObject(3, tempReceipt.getCustomerId(), Types.INTEGER);
		//			stmt.setObject(4, tempReceipt.getSales(), Types.INTEGER);
		//			stmt.setObject(5, tempReceipt.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	@Override
	public void delete(TempReceipt tempReceipt) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "DELETE FROM sales_data"
		//					+ " Where receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, tempReceipt.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	private TempReceipt mapToTempReceipt(ResultSet rs) throws Exception {
		TempReceipt tempReceipt = new TempReceipt();
		tempReceipt.setRoomId((Integer) rs.getObject("store_id"));
		tempReceipt.setStartTime(rs.getTimestamp("start_time"));
		tempReceipt.setCheckOutTime(rs.getTimestamp("checkout_time"));
		tempReceipt.setStayTime((Long) rs.getObject("stay_time"));
		tempReceipt.setCustomerId((Integer) rs.getObject("customer_id"));
		tempReceipt.setCustomerName(rs.getString("customer_name"));
		tempReceipt.setSumPrice((Integer) rs.getObject("sum_price"));
		tempReceipt.setSumTax((Integer)rs.getObject("sum_tax"));
		tempReceipt.setSumDiscount((Integer)rs.getObject("sum_discount"));
		tempReceipt.setRoomPrice((Integer) rs.getObject("room_price"));
		tempReceipt.setRoomTax((Integer)rs.getObject("room_tax"));
		tempReceipt.setPayment((Integer)rs.getObject("payment"));
		tempReceipt.setChangeMoney((Integer)rs.getObject("change_money"));
		tempReceipt.setReceiptId((Integer) rs.getObject("receipt_id"));

		return tempReceipt;
	}



}
