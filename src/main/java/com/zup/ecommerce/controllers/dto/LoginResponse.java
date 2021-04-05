package com.zup.ecommerce.controllers.dto;

public class LoginResponse {

	private String token;
	
	public LoginResponse(String token, String tipo) {
		this.token = tipo+" "+token;
	}
	
	public String getToken() {
		return token;
	}
}
