package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.RoomType;

public class RoomTypeDaoImpl implements RoomTypeDao {
	private DataSource ds;

	public RoomTypeDaoImpl(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<RoomType> findAll() throws Exception {
		List<RoomType> roomTypeList = new ArrayList<>();

		try (Connection con = ds.getConnection()) {
			String sql = "SELECT"
					+ " room_type.room_type_id, room_type.room_type_name"
					+ " FROM room_type";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				roomTypeList.add(mapToRoomType(rs));
			}
		} catch (Exception e) {
			throw e;
		}
		return roomTypeList;
	}

	@Override
	public RoomType findById(Integer id) throws Exception {
		RoomType roomType = null;
		try (Connection con = ds.getConnection()) {
			String sql = "SELECT *"
					+ " FROM room_type"
					+ " WHERE room_type_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, id, Types.INTEGER);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				roomType = mapToRoomType(rs);
			}
		} catch (Exception e) {
			throw e;
		}
		return roomType;
	}

	@Override
	public void insert(RoomType roomType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "INSERT INTO room_type"
					+ " (room_type_id,room_type_name)"
					+ " VALUES (?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomType.getRoomTypeId(),Types.INTEGER);
			stmt.setString(2, roomType.getRoomTypeName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void update(RoomType roomType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "UPDATE roomTypes"
					+ " SET room_type_id = ?, room_type_name = ?"
					+ " WHERE room_type_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomType.getRoomTypeId(), Types.INTEGER);
			stmt.setString(2, roomType.getRoomTypeName());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void delete(RoomType roomType) throws Exception {
		try (Connection con = ds.getConnection()) {
			String sql = "DELETE FROM roomTypes"
					+ " Where room_type_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setObject(1, roomType.getRoomTypeId(),Types.INTEGER);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}

	}

	private RoomType mapToRoomType(ResultSet rs) throws Exception {
		RoomType roomType = new RoomType();
		roomType.setRoomTypeId((Integer) rs.getObject("room_type_id"));
		roomType.setRoomTypeName(rs.getString("room_type_name"));

		return roomType;

	}

}
