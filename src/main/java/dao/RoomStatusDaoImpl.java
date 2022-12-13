package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.RoomStatus;

public class RoomStatusDaoImpl implements RoomStatusDao {
	private DataSource ds;

	public RoomStatusDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<RoomStatus> findAll() throws Exception {
		List<RoomStatus> roomStatusList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM sales_data";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomStatusList.add(mapToRoomStatus(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatusList;
	}

	@Override
	public List<RoomStatus> findbyRoomId(Integer roomId) throws Exception {
		List<RoomStatus> roomStatusList = new ArrayList<>();
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomStatusList.add(mapToRoomStatus(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatusList;
	}

	@Override
	public RoomStatus findById(Integer roomId) throws Exception {
		RoomStatus roomStatus = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomStatus = mapToRoomStatus(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatus;
	}

	@Override
	public RoomStatus checkCurrentUser(Integer customerId) throws Exception {
		RoomStatus roomStatus = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_status"
					+ " WHERE in_use=true AND customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomStatus = mapToRoomStatus(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomStatus;
	}

	@Override
	public void marge(RoomStatus tempReciept) throws Exception {
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
	public void insert(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ "(room_id,"//1
					+ " room_name,"//2
					+ " customer_id,"//3
					+ " customer_name,"//4
					+ " start_time,"//5
					+ " checkout_time,"
					+ " stay_time,"//6
					+ " losst_time,"//7
					+ " plan_id,"//8
					+ " plan_name,"//9
					+ " room_price,"//10
					+ " room_discount,"//11
					+ " room_total_price,"//12
					+ " tax_type,"//13
					+ " tax_name,"//14
					+ " room_tax,"//15
					+ " cleaning_status,"//16
					+ " in_use,"//17
					+ " room_uuid,"//18
					+ " room_pass)"//19
					+ " VALUES (?,?,?,?,?,NOW(),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(), Types.INTEGER);
			stmt.setString(2, roomStatus.getRoomName());
			stmt.setObject(3, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(4, roomStatus.getCustomerName());
			stmt.setObject(5, roomStatus.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(6, roomStatus.getStayTime(), Types.BIGINT);
			stmt.setObject(7, roomStatus.getLosstTime(), Types.INTEGER);
			stmt.setObject(8, roomStatus.getPlanId(), Types.INTEGER);
			stmt.setString(9, roomStatus.getPlanName());
			stmt.setObject(10, roomStatus.getRoomPrice(), Types.INTEGER);
			stmt.setObject(11, roomStatus.getRoomDiscount(), Types.INTEGER);
			stmt.setObject(12, roomStatus.getRoomTotalPrice(), Types.INTEGER);
			stmt.setObject(13, roomStatus.getTaxType(), Types.INTEGER);
			stmt.setString(14, roomStatus.getTaxName());
			stmt.setObject(15, roomStatus.getRoomTax(), Types.INTEGER);
			stmt.setObject(16, roomStatus.getCleaningStatus(), Types.INTEGER);
			stmt.setBoolean(17, roomStatus.getInUse());
			stmt.setString(18, roomStatus.getRoomUuid());
			stmt.setString(19, roomStatus.getRoomPass());
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(RoomStatus roomStatus) throws Exception {
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
			stmt.setString(1, roomStatus.getRoomName());
			stmt.setObject(2, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(3, roomStatus.getCustomerName());
			stmt.setObject(4, roomStatus.getStartTime(), Types.TIMESTAMP);
			stmt.setObject(5, roomStatus.getStayTime(), Types.BIGINT);
			stmt.setObject(6, roomStatus.getLosstTime(), Types.INTEGER);
			stmt.setObject(7, roomStatus.getPlanId(), Types.INTEGER);
			stmt.setString(8, roomStatus.getPlanName());
			stmt.setObject(9, roomStatus.getRoomPrice(), Types.INTEGER);
			stmt.setObject(10, roomStatus.getRoomDiscount(), Types.INTEGER);
			stmt.setObject(11, roomStatus.getRoomTotalPrice(), Types.INTEGER);
			stmt.setObject(12, roomStatus.getTaxType(), Types.INTEGER);
			stmt.setString(13, roomStatus.getTaxName());
			stmt.setObject(14, roomStatus.getRoomTax(), Types.INTEGER);
			stmt.setObject(15, roomStatus.getCleaningStatus(), Types.INTEGER);
			stmt.setBoolean(16, roomStatus.getInUse());
			stmt.setString(17, roomStatus.getRoomUuid());
			stmt.setString(18, roomStatus.getRoomPass());
			stmt.setObject(19, roomStatus.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void cleaning(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ " (room_id,cleaning_status)"
					+ " VALUES(?,?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " room_id = VALUES (room_id),"
					+ " cleaning_status = VALUES (cleaning_status)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(), Types.INTEGER);
			stmt.setObject(2, roomStatus.getCleaningStatus(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void checkIn(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ " (room_id,customer_id,customer_name,start_time,in_use)"
					+ " VALUES(?,?,?,NOW(),?)"
					+ " ON DUPLICATE KEY UPDATE"
					+ " room_id = VALUES (room_id),"
					+ " customer_id = VALUES (customer_id),"
					+ " customer_name = VALUES (customer_name),"
					+ " start_time = VALUES (start_time),"
					+ " in_use = VALUES (in_use)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(), Types.INTEGER);
			stmt.setObject(2, roomStatus.getCustomerId(), Types.INTEGER);
			stmt.setString(3, roomStatus.getCustomerName());
			stmt.setBoolean(4, true);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void checkOut(RoomStatus roomStatus) throws Exception {
//		try (Connection con = ds.getConnection()) {
//			String sql = "UPDATE rooms"
//					+ " SET in_use = false,"
//					+ " cleaning_id = 2"
//					+ " WHERE room_id = ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setObject(1, room.getRoomId(), Types.INTEGER);
//			stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		}

	}

	@Override
	public void delete(RoomStatus roomStatus) throws Exception {
		//		try (Connection con = ds.getConnection()) {
		//			String sql = "DELETE FROM sales_data"
		//					+ " Where receipt_id = ?";
		//			PreparedStatement stmt = con.prepareStatement(sql);
		//			stmt.setObject(1, roomStatus.getReceiptId(), Types.INTEGER);
		//			stmt.executeUpdate();
		//		} catch (Exception e) {
		//			throw e;
		//		}

	}

	private RoomStatus mapToRoomStatus(ResultSet rs) throws Exception {
		RoomStatus roomStatus = new RoomStatus();

		roomStatus.setRoomId((Integer) rs.getObject("room_id"));
		roomStatus.setRoomName(rs.getString("room_name"));
		roomStatus.setCustomerId((Integer) rs.getObject("customer_id"));
		roomStatus.setCustomerName(rs.getString("customer_name"));
		roomStatus.setStartTime(rs.getTimestamp("start_time"));
		roomStatus.setCheckOutTime(rs.getTimestamp("checkout_time"));
		roomStatus.setStayTime((Long) rs.getObject("stay_time"));
		roomStatus.setStayTime((Long) rs.getObject("losst_time"));
		roomStatus.setPlanId((Integer) rs.getObject("plan_id"));
		roomStatus.setPlanName(rs.getString("plan_name"));
		roomStatus.setRoomPrice((Integer) rs.getObject("room_price"));
		roomStatus.setRoomDiscount((Integer) rs.getObject("room_discount"));
		roomStatus.setRoomTotalPrice((Integer) rs.getObject("room_total_price"));
		roomStatus.setTaxType((Integer) rs.getObject("tax_type"));
		roomStatus.setTaxName(rs.getString("tax_name"));
		roomStatus.setRoomTax((Integer) rs.getObject("room_tax"));
		roomStatus.setCleaningStatus((Integer) rs.getObject("cleaning_status"));
		roomStatus.setInUse(rs.getBoolean("in_use"));
		roomStatus.setRoomUuid(rs.getString("room_uuid"));
		roomStatus.setRoomPass(rs.getString("room_pass"));

		return roomStatus;
	}

}
