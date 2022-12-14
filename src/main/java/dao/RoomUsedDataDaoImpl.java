package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.RoomUsedData;

public class RoomUsedDataDaoImpl implements RoomUsedDataDao {
	private DataSource ds;

	public RoomUsedDataDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<RoomUsedData> findAll() throws Exception {
		List<RoomUsedData> roomUsedDataList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_data";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomUsedDataList.add(mapToRoomUsedData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomUsedDataList;
	}

	@Override
	public List<RoomUsedData> findbyRoomId(Integer roomId) throws Exception {
		List<RoomUsedData> roomUsedDataList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomUsedDataList.add(mapToRoomUsedData(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomUsedDataList;
	}

	@Override
	public RoomUsedData findById(Integer roomId) throws Exception {
		RoomUsedData roomUsedData = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomUsedData = mapToRoomUsedData(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomUsedData;
	}

	@Override
	public void marge(RoomUsedData tempReciept) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, tempReciept.getRoomId(), Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				update(tempReciept);
			} else {
				insert(tempReciept);
			}
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void insert(RoomUsedData roomUsedData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ "(receipt_id,"//1
					+ " room_id,"//2
					+ " customer_id,"//3
					+ " start_time,"//4
					+ " checkout_time,"//5
					+ " stay_time,"//6
					+ " losst_time,"//7
					+ " plan_id,"//8
					+ " room_price,"//9
					+ " room_discount,"//10
					+ " room_total_price,"//11
					+ " tax_type,"//12
					+ " room_tax)"//13
					+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomUsedData.getReceiptId(), Types.INTEGER);
			stmt.setObject(2, roomUsedData.getRoomId(), Types.INTEGER);
			stmt.setObject(3, roomUsedData.getCustomerId(), Types.INTEGER);
			stmt.setObject(4, roomUsedData.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(5, roomUsedData.getCheckOutTime(), Types.TIMESTAMP);
			stmt.setObject(6, roomUsedData.getStayTime(), Types.BIGINT);
			stmt.setObject(7, roomUsedData.getLosstTime(), Types.INTEGER);
			stmt.setObject(8, roomUsedData.getPlanId(), Types.INTEGER);
			stmt.setObject(9, roomUsedData.getRoomPrice(), Types.INTEGER);
			stmt.setObject(10, roomUsedData.getRoomDiscount(), Types.INTEGER);
			stmt.setObject(11, roomUsedData.getRoomTotalPrice(), Types.INTEGER);
			stmt.setObject(12, roomUsedData.getRoomTax(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(RoomUsedData roomUsedData) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE room_status"
					+ " SET room_name,"//1
					+ " customer_id,"//2
					+ " customer_name,"//3
					+ " start_time,"//4
					+ " checkout_time,"
					+ " stay_time,"//5
					+ " losst_time,"//6
					+ " plan_id,"//7
					+ " plan_name,"//8
					+ " room_price,"//9
					+ " room_discount,"//10
					+ " room_total_price,"//11
					+ " tax_type,"//12
					+ " tax_name,"//13
					+ " room_tax,"//14
					+ " cleaning_status,"//15
					+ " in_use,"//16
					+ " room_uuid,"//17
					+ " room_pass)"//18
					+ " WHERE room_id=?";//19
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, roomUsedData.getRoomName());
			stmt.setObject(2, roomUsedData.getCustomerId(), Types.INTEGER);
			stmt.setString(3, roomUsedData.getCustomerName());
			stmt.setObject(4, roomUsedData.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(5, roomUsedData.getStayTime(), Types.BIGINT);
			stmt.setObject(6, roomUsedData.getLosstTime(), Types.INTEGER);
			stmt.setObject(7, roomUsedData.getPlanId(), Types.INTEGER);
			stmt.setString(8, roomUsedData.getPlanName());
			stmt.setObject(9, roomUsedData.getRoomPrice(), Types.INTEGER);
			stmt.setObject(10, roomUsedData.getRoomDiscount(), Types.INTEGER);
			stmt.setObject(11, roomUsedData.getRoomTotalPrice(), Types.INTEGER);
			stmt.setObject(12, roomUsedData.getTaxType(), Types.INTEGER);
			stmt.setString(13, roomUsedData.getTaxName());
			stmt.setObject(14, roomUsedData.getRoomTax(), Types.INTEGER);
			stmt.setObject(18, roomUsedData.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}


	@Override
	public void delete(RoomUsedData roomUsedData) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "DELETE FROM sales_data"
		//					+ " Where receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, roomUsedData.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	private RoomUsedData mapToRoomUsedData(ResultSet rs) throws Exception {
		RoomUsedData roomUsedData = new RoomUsedData();
		roomUsedData.setRoomUsedDataId((Integer) rs.getObject("room_used_data_id"));
		roomUsedData.setReceiptId((Integer) rs.getObject("receipt_id"));
		roomUsedData.setRoomId((Integer) rs.getObject("room_id"));
		roomUsedData.setCustomerId((Integer) rs.getObject("customer_id"));
		roomUsedData.setCustomerName(rs.getString("customer_name"));
		roomUsedData.setStartTime(rs.getTimestamp("start_time"));
		roomUsedData.setCheckOutTime(rs.getTimestamp("checkout_time"));
		roomUsedData.setStayTime((Long) rs.getObject("stay_time"));
		roomUsedData.setStayTime((Long) rs.getObject("losst_time"));
		roomUsedData.setPlanId((Integer) rs.getObject("plan_id"));
		roomUsedData.setPlanName(rs.getString("plan_name"));
		roomUsedData.setRoomPrice((Integer) rs.getObject("room_price"));
		roomUsedData.setRoomDiscount((Integer) rs.getObject("room_discount"));
		roomUsedData.setRoomTotalPrice((Integer) rs.getObject("room_total_price"));
		roomUsedData.setTaxType((Integer) rs.getObject("tax_type"));
		roomUsedData.setTaxName(rs.getString("tax_name"));

		return roomUsedData;
	}

}
