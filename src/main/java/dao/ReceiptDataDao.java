package dao;

import java.util.List;

import domain.ExitReceiptData;

public interface ReceiptDataDao {
	List<ExitReceiptData> findAll() throws Exception;
	ExitReceiptData findById(Integer receiptId) throws Exception;
	Integer insert (ExitReceiptData salesData) throws Exception;
	void update (ExitReceiptData salesData) throws Exception;
	void delete (ExitReceiptData salesData) throws Exception;
}
