package com.bridgeit.fundoo.dao;

import java.util.List;

import com.bridgeit.fundoo.model.User;

public interface IUserDao {

	boolean save(User user);

	List<User> getAllUser();



	User getUser(Integer id);
	
	User getUserByEmail(String email);

	boolean updateUser(User updateUser);

	boolean deleteUser(User updateUser);


}
