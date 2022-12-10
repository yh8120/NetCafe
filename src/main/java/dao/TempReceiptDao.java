package dao;

import java.util.List;

import domain.TempReceipt;

public interface TempReceiptDao {
	List<TempReceipt> findAll() throws Exception;

	List<TempReceipt> findbyRoomId(Integer roomId) throws Exception;

	TempReceipt findById(Integer tempReceiptId) throws Exception;

	void marge(TempReceipt tempReciept) throws Exception;

	void insert(TempReceipt tempReciept) throws Exception;

	void update(TempReceipt tempReciept) throws Exception;

	void delete(TempReceipt tempReciept) throws Exception;
}
