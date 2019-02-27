package com.bridgeit.fundoo.dto;

public class UserDto 
{
private String name;
private String email;
private int userId;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
@Override
public String toString() {
	return "UserDto [name=" + name + ", email=" + email + ", userId=" + userId + "]";
}

}
