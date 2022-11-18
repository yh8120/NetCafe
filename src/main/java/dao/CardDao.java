package dao;

import java.util.List;

import domain.Card;

public interface CardDao {
	List<Card> findAll() throws Exception;
	Card findById(Integer id) throws Exception;
	void insert (Card card) throws Exception;
	void update (Card card) throws Exception;
	void delete (Card card) throws Exception;
}
