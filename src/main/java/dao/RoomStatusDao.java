package dao;

import java.util.List;

import domain.RoomStatus;

public interface RoomStatusDao {
	List<RoomStatus> findAll() throws Exception;
	List<RoomStatus> findbyRoomId(Integer roomId) throws Exception;	
	RoomStatus checkCurrentUser(Integer customerId)throws Exception;
	RoomStatus findById(Integer roomId) throws Exception;
	void marge(RoomStatus roomStatus) throws Exception;
	void insert(RoomStatus roomStatus) throws Exception;
	void update(RoomStatus roomStatus) throws Exception;
	void cleaning(RoomStatus roomStatus) throws Exception;
	void checkIn(RoomStatus roomStatus) throws Exception;
	void checkOut(RoomStatus roomStatus) throws Exception;
	void delete(RoomStatus roomStatus) throws Exception;
}
