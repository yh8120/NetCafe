package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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
					+ " FROM room_status";
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
	public void insert(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_status"
					+ " (room_id,customer_id,started)"
					+ " VALUES (?,?,NOW())";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(),Types.INTEGER);
			stmt.setObject(2, roomStatus.getCustomerId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE room_status"
					+ " SET customer_id=?,started=?"
					+ " WHERE room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getCustomerId(),Types.INTEGER);
			stmt.setObject(2, roomStatus.getStarted(),Types.DATE);
			stmt.setObject(3, roomStatus.getRoomId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(RoomStatus roomStatus) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM room_status"
					+ " Where room_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomStatus.getRoomId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private RoomStatus mapToRoomStatus(ResultSet rs) throws Exception {
		RoomStatus roomStatus = new RoomStatus();
		roomStatus.setRoomId((Integer) rs.getObject("room_id"));
		roomStatus.setCustomerId((Integer) rs.getObject("customer_id"));
		roomStatus.setStarted((Date) rs.getObject("started"));

		return roomStatus;

	}

}
