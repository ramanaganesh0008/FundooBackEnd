package com.bridgeit.fundoo.model;

import com.bridgeit.fundoo.dto.UserDto;

public class Response
{
	private int statusCode;
	private String status;
	private UserDto data;

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public UserDto getData() {
		return data;
	}

	public void setData(UserDto user) {
		this.data = user;
	}
}
