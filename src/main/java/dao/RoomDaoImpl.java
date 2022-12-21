package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Room;

public class RoomDaoImpl implements RoomDao {
	private DataSource ds;

	public RoomDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<Room> findAll() throws Exception {
		List<Room> roomList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM rooms"
					+ " JOIN room_types ON rooms.room_type_id = room_types.room_type_id"
					+ " LEFT OUTER JOIN room_status ON rooms.room_id = room_status.room_id"
					+ " LEFT OUTER JOIN cleaning_status ON rooms.cleaning_status = cleaning_status.cleaning_id"
					+ " ORDER BY room_order ASC";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomList.add(mapToRoom(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomList;
	}

	@Override
	public Room findById(Integer roomId) throws Exception {
		Room room = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT * FROM rooms"
					+ " JOIN room_types ON rooms.room_type_id = room_types.room_type_id"
					+ " LEFT OUTER JOIN room_status ON rooms.room_id = room_status.room_id"
					+ " LEFT OUTER JOIN cleaning_status ON rooms.cleaning_status = cleaning_status.cleaning_id"
					+ " WHERE rooms.room_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomId, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				room = mapToRoom(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return room;
	}


	@Override
	public void cleaning(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET cleaning_status = ?"
					+ " WHERE room_id = ?";
			//					"INSERT INTO rooms"
			//					+ " (room_id,cleaning_status)"
			//					+ " VALUES(?,?)"
			//					+ " ON DUPLICATE KEY UPDATE"
			//					+ " room_id = VALUES (room_id),"
			//					+ " cleaning_status = VALUES (cleaning_status)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getCleaningStatus(), Types.INTEGER);
			stmt.setObject(2, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void insert(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO rooms"
					+ " (room_name,room_type_id)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, room.getRoomName());
			stmt.setObject(2, room.getRoomTypeId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET room_name = ?,room_type_id = ?,room_order = ?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, room.getRoomName());
			stmt.setObject(2, room.getRoomTypeId(), Types.INTEGER);
			stmt.setObject(3, room.getRoomOrder(), Types.INTEGER);
			stmt.setObject(4, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM rooms"
					+ " Where room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private Room mapToRoom(ResultSet rs) throws Exception {
		Room room = new Room();
		room.setRoomId((Integer) rs.getObject("room_id"));
		room.setRoomName(rs.getString("room_name"));
		room.setRoomTypeId((Integer) rs.getObject("room_type_id"));
		room.setRoomTypeName(rs.getString("room_type_name"));
		room.setRoomOrder((Integer) rs.getObject("room_order"));
		room.setCleaningStatus((Integer) rs.getObject("cleaning_status"));
		room.setCleaningName(rs.getString("cleaning_name"));
		room.setCustomerId((Integer) rs.getObject("customer_id"));
		room.setCustomerName(rs.getString("customer_name"));
		room.setStartTime(rs.getTimestamp("start_time"));
		room.setLosstTime((Long) rs.getObject("losst_time"));
		room.setRoomDiscount((Integer) rs.getObject("room_discount"));
		room.setRoomUuid(rs.getString("room_uuid"));
		room.setInUse(rs.getBoolean("in_use"));
		return room;
	}

}
