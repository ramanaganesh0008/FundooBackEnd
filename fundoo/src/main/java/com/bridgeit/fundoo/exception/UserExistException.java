package com.bridgeit.fundoo.exception;

public class UserExistException extends Exception
{
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int code;
String msg;


public UserExistException() 
 {
	 super();
 }
 
public UserExistException(int code,String msg)
{
	this.code=code;
	this.msg=msg;
}
}
