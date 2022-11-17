package dao;

import java.util.List;

import domain.RoomType;

public interface RoomTypeDao {
	List<RoomType> findAll() throws Exception;
	RoomType findById(Integer id) throws Exception;
	void insert (RoomType roomType) throws Exception;
	void update (RoomType roomType) throws Exception;
	void delete (RoomType roomType) throws Exception;
}
