package dao;

import java.util.List;

import domain.RoomStatus;

public interface RoomStatusDao {
	List<RoomStatus> findAll() throws Exception;
	RoomStatus findById(Integer id) throws Exception;
	void insert (RoomStatus roomStatus) throws Exception;
	void update (RoomStatus roomStatus) throws Exception;
	void delete (RoomStatus roomStatus) throws Exception;
}
