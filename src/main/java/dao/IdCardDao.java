package dao;

import java.util.List;

import domain.IdCard;

public interface IdCardDao {
	List<IdCard> findAll() throws Exception;
	IdCard findById(Integer cardId) throws Exception;
	void insert (IdCard idCard) throws Exception;
	void update (IdCard idCard) throws Exception;
	void delete (IdCard idCard) throws Exception;
}
