package dao;

import java.sql.Time;
import java.util.List;

import domain.PricePlan;

public interface PricePlanDao {
	List<PricePlan> findAll() throws Exception;
	List<PricePlan> findByNow(Time now) throws Exception;
	PricePlan findById(Integer id) throws Exception;
	void insert (PricePlan priceplan) throws Exception;
	void update (PricePlan priceplan) throws Exception;
	void delete (PricePlan priceplan) throws Exception;
}
