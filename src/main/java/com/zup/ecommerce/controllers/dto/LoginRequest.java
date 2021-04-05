package com.zup.ecommerce.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

	@Email @NotBlank
	private String email;
	
	@NotBlank
	private String senha;

	public LoginRequest(@NotBlank String email, @NotBlank String senha) {
		this.email = email;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
