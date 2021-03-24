package com.zup.ecommerce.controllers.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zup.ecommerce.models.Usuario;

public class UsuarioRequest {

	@NotEmpty @Email
	private String email;
	
	@NotEmpty @Size(min=6, max=50)
	private String senha;

	public UsuarioRequest(@NotEmpty @Email String email, @NotEmpty @Length(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario converter() {
		senha = new BCryptPasswordEncoder().encode(senha);
		return new Usuario(email, senha, LocalDateTime.now());
	}
	
}
