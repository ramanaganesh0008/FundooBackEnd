package com.bridgeit.fundoo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.IUserDao;
import com.bridgeit.fundoo.dto.UserDto;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.utility.UserToken;
import com.bridgeit.fundoo.utility.Utility;

@Transactional
public class UserServiceImplementation implements IUserService{

	@Autowired
	IUserDao userDao;
	
	@Autowired
	String key;
	
	@Override
	public boolean addUser(User user) 
	{
	//	System.out.println(userDao+" "+key);
		String encryptedPassword=Utility.encrypt(user.getPassword(), key);
		//System.out.println(encryptedPassword);
		user.setPassword(encryptedPassword);
		user.setIsActive(false);
		boolean check=userDao.save(user);
		return check;
	}
	@Override
	public boolean sendMail(User user) {
		
		List<User> userList=userDao.getAllUser();
		for (int i = 0; i < userList.size(); i++) 
		{
			if(user.getEmail().equals(userList.get(i).getEmail()) && user.isIsActive()==false)
			{
				
				
				String token;
				try {
					token = UserToken.generateToken(userList.get(i).getUserId());
					Utility.sendEmail(user, token,"verifyToken");
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				return true;
			}
		}
		System.out.println("already exist");
		return false;
		
	}


	@Override
	public boolean updateUser(Integer id) 
	{
		User updateUser=userDao.getUser(id);
		updateUser.setIsActive(true);
		userDao.updateUser(updateUser);
		return true;
	}
	@Override
	public boolean deleteUser(Integer id)
	{
		User updateUser=userDao.getUser(id);

		boolean check=userDao.deleteUser(updateUser);
		if(check)
			return true;
		return false;
	}
	@Override
	public UserDto userLogin(User user) 
	{
		
		List<User> userList=userDao.getAllUser();
		for (int i = 0; i < userList.size(); i++)
		{
			String encrptedPassword=Utility.encrypt(user.getPassword(), key);
			if(user.getEmail().equals(userList.get(i).getEmail()) && encrptedPassword.equals(userList.get(i).getPassword()))
			{
				ModelMapper mapper=new ModelMapper();
				UserDto loginUser=mapper.map(userList.get(i),UserDto.class);
				System.out.println("login user "+loginUser);
				return loginUser;
			}
		}
		return null;
	}
	@Override
	public User getUser(Integer id) {
		User user=userDao.getUser(id);
		return user;
	}
	@Override
	public boolean forgetPassword(User user) 
	{
		List<User> userList=userDao.getAllUser();
	
		for (int i = 0; i < userList.size(); i++) 
		{
			if(user.getEmail().equals(userList.get(i).getEmail()))
			{
			
					try {
						String token=UserToken.generateToken(userList.get(i).getUserId());
						
						token=token+"/"+user.getPassword();
						Utility.sendEmail(user, token,"resetPassword");
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
			return false;
	}

	@Override
	public boolean resetUserPassword(String userPassword, String token) {
		int userId;
		try {
			userId = UserToken.tokenVerify(token);
			User user=userDao.getUser(userId);
			String encrptedPassword=Utility.encrypt(userPassword, key);
			user.setPassword(encrptedPassword);
			userDao.updateUser(user);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	
	
	
//	@Override
//	public boolean forgetVerification(GenerateOtp generateOtp,User forgetUser) 
//	{
//		List<GenerateOtp> userOtp=userDao.getAllOtp();
//		List<User> userList=userDao.getAllUser();
//		for (int i = 0; i <userOtp.size(); i++) 
//		{
//			if(generateOtp.getOtpPassword().equals(userOtp.get(i).getOtpPassword()))
//			{
//				for (int j = 0; j < userList.size(); j++) 
//				{
//					
//					if(userList.get(i).getEmail().equals(forgetUser.getEmail()))
//					{
//						String encryptedPassword=Utility.encrypt(forgetUser.getPassword(), key);
//						User newUser=userList.get(i);
//						newUser.setPassword(encryptedPassword);
//						boolean check=userDao.updateUser(newUser);
//						if(check)
//						{
//							return true;
//						}
//					}
//				}
//			}
//		}
//		return false;
//
//	}

//	@Override
//	public boolean verifyOtp(GenerateOtp userOtp) 
//	{
//		
//		List<GenerateOtp> otp=userDao.getAllOtp();
//		System.out.println("hdjsg");
//		System.out.println(userOtp.getEmail()+" "+userOtp.getOtpPassword()+" "+otp.size());
//		for (int i = 0; i < otp.size(); i++)
//		{
//			if(userOtp.getOtpPassword().equals(otp.get(i).getOtpPassword()))
//			{
//				System.out.println("hi");
//				return true;
//			}
//		}
//		return false;
//	}
}
