package com.bridgeit.fundoo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoo.model.User;

@Repository	
public class UserDao implements IUserDao{

	@Autowired
	SessionFactory factory;
	
	@Override
	public boolean save(User user) 
	{
		if(factory!=null)
		{
			
			System.out.println(factory);
			System.out.println("successfull");
			factory.getCurrentSession().save(user);
			return true;
		}
		//factory.getCurrentSession().save(user);
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() 
	{
		Query query=factory.getCurrentSession().createQuery("from User");
		List<User> userList=query.list();
		return userList;
	}
	
	@Override
	public User getUser(Integer userId) {
		System.out.println(userId);
		User user=(User) factory.getCurrentSession().get(User.class,userId);
		System.out.println(user);
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		System.out.println(email);
		User user=(User) factory.getCurrentSession().get(User.class,email);
		System.out.println(user);
		return user;
	}
	
	@Override
	public boolean updateUser(User updateUser) 
	{
		if(factory!=null)
		{
			factory.getCurrentSession().update(updateUser);
			System.out.println("update successfull");
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteUser(User updateUser) {
		if(factory!=null)
		{
			factory.getCurrentSession().delete(updateUser);
			System.out.println("deleted successfull");
			return true;
		}
		return false;
	}


	

}
