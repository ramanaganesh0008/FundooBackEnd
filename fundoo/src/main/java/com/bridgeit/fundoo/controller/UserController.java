package com.bridgeit.fundoo.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.dto.UserDto;
import com.bridgeit.fundoo.exception.UserExistException;
import com.bridgeit.fundoo.model.Response;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.service.IUserService;
import com.bridgeit.fundoo.utility.UserToken;

@RestController
//@RequestMapping("/fundoo")

@CrossOrigin(origins= {"*"}, exposedHeaders= {"jwtTokenxxx"})
//@CrossOrigin
public class UserController {


	static final Logger logger=LoggerFactory.getLogger(UserController.class);	

	User tempUser,forgetUser;
	Response response;
	@Autowired
	IUserService userService;
	
	
	
	@RequestMapping("/")
	public String welcome()
	{
		return "welcome";
	}
	
	
/**
 * 
 * @param user contains user details
 * @param httpResponse used for return user data
 * @return
 * @throws UserExistException 
 */
	@RequestMapping(value="/login", method=RequestMethod.POST )
	public ResponseEntity<Response> userLogin(@RequestBody User user,HttpServletResponse httpResponse) throws UserExistException
	{
		System.out.println("login");
		logger.info("user login");
		UserDto loginUser=userService.userLogin(user);
		response=new Response();
		
		if(loginUser==null)
		{
			response.setStatusCode(404);
			response.setStatus("user is not valid");
			throw new UserExistException(404,"User Not Valid");
		}
		response.setStatusCode(166);
		response.setStatus("login successfully");
		response.setData(loginUser);
		String token;
		try {
			token = UserToken.generateToken(loginUser.getUserId());
			httpResponse.addHeader("jwtTokenxxx", token);
			System.out.println("response data = "+response.getData());
			//System.out.println(token);
			
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param user contains user data
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Response> sendOtp(@RequestBody User user)
	{
		//logger.info("User Registration");
		
		System.out.println(userService);
		boolean check=userService.addUser(user);
		response=new Response();

		if(check)
		{
			userService.sendMail(user);
			response.setStatusCode(200);
			response.setStatus("mail send successfull");
			return new ResponseEntity<Response>(response,HttpStatus.OK);	
		}

	
	//	System.out.println("hihi");
		response.setStatusCode(0);
		response.setStatus("Account already exist");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
	/**
	 * 
	 * @param token contains token of user
	 * @return
	 */
	@RequestMapping("/verifyToken/{token:.+}")
	public ResponseEntity<String> verifyUser(@PathVariable String token)
	{
		try {
			int userId=UserToken.tokenVerify(token);
			userService.updateUser(userId);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Account Activated Successfully",HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param user contains user data
	 * @return
	 */
	@RequestMapping(value="/forgetPassword", method=RequestMethod.POST)
	public ResponseEntity<Response > forgetPassword(@RequestBody User user)
	{
		
		boolean check=userService.forgetPassword(user);
		response=new Response();
		if(check)
		{
			response.setStatusCode(200);
			response.setStatus("otp send successfully");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatusCode(404);
		response.setStatus("email id is not valid");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
				
	}
	
	/**
	 * 
	 * @param userPassword contains user password
	 * @param token contains user token
	 * @return
	 */
	@RequestMapping("/resetPassword/{token:.+}/{userPassword}")
	public ResponseEntity<String> resetPassword(@PathVariable String userPassword,@PathVariable String token)
	{
		userService.resetUserPassword(userPassword,token);
		return new ResponseEntity<String>("Password Reset Successfully",HttpStatus.OK);
	}
	
	

	/**
	 * 
	 * @param user contains update user details 
	 * @param id contains userId
	 * @return
	 */
	@RequestMapping("/updateUser/{id}")
	public ResponseEntity<Response> updateUser(@RequestBody User user,@PathVariable Integer id)
	{
		boolean check=userService.updateUser(id);
		response=new Response();
		if(check)
		{
			response.setStatus("updated successfully");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatus("id is not valid");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
		
	}
	
	
	/**
	 * 
	 * @param id contains userId
	 * @return
	 */
	@RequestMapping("/deleteUser/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer id)
	{
		boolean check=userService.deleteUser(id);
		response=new Response();
		if(check)
		{
			response.setStatus("delete successfully");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatus("user is not found");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}
	
	
	

	
//	@RequestMapping("/registerUser")
//	public ResponseEntity<Response> registerUser()
//	{
//		boolean check=userService.addUser(tempUser);
//		response=new Response();
//		if(check)
//		{
//			response.setStatusCode(200);
//			response.setStatus("register successfull");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatus("register unsuccessfull");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//	}
//	
//	
//	
//	@RequestMapping(value="/forgetOtpVerification", method=RequestMethod.POST)
//	public ResponseEntity<Response> forgetOtpVerify(@RequestBody GenerateOtp generateOtp)
//	{
//		boolean check=userService.forgetVerification(generateOtp,forgetUser);
//		response=new Response();
//		if(check)
//		{
//			response.setStatusCode(200);
//			response.setStatus("reset successfully");
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
//		response.setStatusCode(404);
//		response.setStatus("otp is wrong");
//		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
//	}
	
}
