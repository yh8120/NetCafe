package dao;

import java.util.List;

import domain.Room;

public interface RoomDao {
	List<Room> findAll() throws Exception;
	Room findById(Integer roomId) throws Exception;
	void insert (Room room) throws Exception;
	void update (Room room) throws Exception;
	void delete (Room room) throws Exception;
}
