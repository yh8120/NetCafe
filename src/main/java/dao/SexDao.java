package dao;

import java.util.List;

import domain.Sex;

public interface SexDao {
	List<Sex> findAll() throws Exception;
	Sex findById(Integer id) throws Exception;
	void insert (Sex sex) throws Exception;
	void update (Sex sex) throws Exception;
	void delete (Sex sex) throws Exception;
}
