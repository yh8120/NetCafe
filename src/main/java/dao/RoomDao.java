package dao;

import java.util.List;

import domain.Room;

public interface RoomDao {
	List<Room> findAll() throws Exception;
	Room findById(Integer roomId) throws Exception;
	Room checkCurrentUser(Integer customerId) throws Exception;
	void insert (Room room) throws Exception;
	void update (Room room) throws Exception;
	void cleaning(Room room) throws Exception;
	void checkIn(Room room) throws Exception;
	void preCheckOut(Room room) throws Exception;
	void checkOut(Room room) throws Exception;
	void delete (Room room) throws Exception;
}
