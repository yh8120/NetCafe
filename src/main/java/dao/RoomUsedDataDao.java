package dao;

import java.util.List;

import domain.RoomUsedData;

public interface RoomUsedDataDao {
	List<RoomUsedData> findAll() throws Exception;
	List<RoomUsedData> findbyRoomId(Integer roomId) throws Exception;
	RoomUsedData findById(Integer roomId) throws Exception;
	void marge(RoomUsedData roomUsedData) throws Exception;
	void insert(RoomUsedData roomUsedData) throws Exception;
	void update(RoomUsedData roomUsedData) throws Exception;
	void delete(RoomUsedData roomUsedData) throws Exception;
}
