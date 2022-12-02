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
			String sql = "SELECT *"
					+ " FROM rooms"
					+ " JOIN room_types ON rooms.room_type_id = room_types.room_type_id"
					+ " JOIN cleaning_status ON rooms.cleaning_id = cleaning_status.cleaning_id"
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
			String sql = "SELECT *"
					+ " FROM rooms"
					+ " JOIN room_types ON rooms.room_type_id = room_types.room_type_id"
					+ " JOIN cleaning_status ON rooms.cleaning_id = cleaning_status.cleaning_id"
					+ " WHERE room_id=?";
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
	public Room checkCurrentUser(Integer customerId) throws Exception {
		Room room = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM rooms"
					+ " JOIN room_types ON rooms.room_type_id = room_types.room_type_id"
					+ " JOIN cleaning_status ON rooms.cleaning_id = cleaning_status.cleaning_id"
					+ " WHERE in_use=true AND customer_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, customerId, Types.INTEGER);
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
					+ " SET room_name = ?,room_type_id = ?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, room.getRoomName());
			stmt.setObject(2, room.getRoomTypeId(), Types.INTEGER);
			stmt.setObject(3, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void cleaning(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET cleaning_id = ?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getCleaningId(), Types.INTEGER);
			stmt.setObject(2, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void checkIn(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET customer_id = ?,"
					+ " started = NOW(),"
					+ " in_use = ?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getCustomerId(), Types.INTEGER);
			stmt.setBoolean(2, true);
			stmt.setObject(3, room.getRoomId(), Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void preCheckOut(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET staying_time = ?,"
					+ " checkout_time = NOW(),"
					+ " subtotal = ?,"
					+ " inner_tax = ?,"
					+ " sum_price = ?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getStayingTime(), Types.BIGINT);
			stmt.setObject(2, room.getSubtotal(), Types.INTEGER);
			stmt.setObject(3, room.getInnerTax(), Types.INTEGER);
			stmt.setObject(4, room.getSumPrice(), Types.INTEGER);
			stmt.setObject(5, room.getRoomId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void checkOut(Room room) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE rooms"
					+ " SET in_use = false"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, room.getRoomId(), Types.INTEGER);
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
		room.setCustomerId((Integer) rs.getObject("customer_Id"));
		room.setStarted(rs.getTimestamp("started"));
		room.setCleaningId((Integer) rs.getObject("cleaning_id"));
		room.setCleaningName(rs.getString("cleaning_name"));
		room.setStayingTime((Long) rs.getObject("staying_time"));
		room.setCheckOutTime(rs.getTimestamp("checkout_time"));
		room.setSubtotal((Integer) rs.getObject("subtotal"));
		room.setSumPrice((Integer) rs.getObject("sum_price"));
		room.setInnerTax((Integer) rs.getObject("inner_tax"));
		room.setInUse(rs.getBoolean("in_use"));
		return room;
	}

}
