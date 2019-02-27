package com.bridgeit.fundoo.service;

import com.bridgeit.fundoo.dto.UserDto;
import com.bridgeit.fundoo.model.User;

public interface IUserService {

	boolean addUser(User user);

	
	boolean sendMail(User user);

	


	boolean updateUser(Integer id);

	
	boolean deleteUser(Integer id);

	
	UserDto userLogin(User user);
	
	
	public User getUser(Integer id);

	
	boolean forgetPassword(User user);


	boolean resetUserPassword(String userPassword, String token);

}
