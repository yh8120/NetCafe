package dao;

import java.util.List;

import domain.UserClass;

public interface UserClassDao {
	List<UserClass> findAll() throws Exception;
	UserClass findById(Integer id) throws Exception;
	void insert (UserClass userClass) throws Exception;
	void update (UserClass userClass) throws Exception;
	void delete (UserClass userClass) throws Exception;
}
