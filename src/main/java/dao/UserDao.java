package dao;

import java.util.List;

import model.User;

public interface UserDao {

	User findByUserName(String name);
	
	List<User> findAll();

}

