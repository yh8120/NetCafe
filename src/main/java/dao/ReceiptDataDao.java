package dao;

import java.util.List;

import domain.ReceiptData;

public interface ReceiptDataDao {
	List<ReceiptData> findAll() throws Exception;
	ReceiptData findById(Integer receiptId) throws Exception;
	void insert (ReceiptData salesData) throws Exception;
	void update (ReceiptData salesData) throws Exception;
	void delete (ReceiptData salesData) throws Exception;
}
