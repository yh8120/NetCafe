package dao;

import java.util.List;

import domain.Room;

public interface RoomDao {
	List<Room> findAll() throws Exception;
	Room findById(Integer id) throws Exception;
	void insert (Room location) throws Exception;
	void update (Room location) throws Exception;
	void delete (Room location) throws Exception;
}
