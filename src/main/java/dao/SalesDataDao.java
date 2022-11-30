package dao;

import java.util.List;

import domain.SalesData;

public interface SalesDataDao {
	List<SalesData> findAll() throws Exception;
	SalesData findById(Integer salesDataId) throws Exception;
	void insert (SalesData salesData) throws Exception;
	void update (SalesData salesData) throws Exception;
	void delete (SalesData salesData) throws Exception;
}
