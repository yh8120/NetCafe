package dao;

import java.util.List;

import domain.Customer;

public interface CustomerDao {
	List<Customer> findAll() throws Exception;
	Customer findById(Integer id) throws Exception;
	void insert (Customer customer) throws Exception;
	void insertWithCheck (Customer customer) throws Exception;
	void update (Customer customer) throws Exception;
	void delete (Customer customer) throws Exception;
}
