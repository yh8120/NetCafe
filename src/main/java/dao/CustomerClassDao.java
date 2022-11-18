package dao;

import java.util.List;

import domain.CustomerClass;

public interface CustomerClassDao {
	List<CustomerClass> findAll() throws Exception;
	CustomerClass findById(Integer id) throws Exception;
	void insert (CustomerClass customerClass) throws Exception;
	void update (CustomerClass customerClass) throws Exception;
	void delete (CustomerClass customerClass) throws Exception;
}
