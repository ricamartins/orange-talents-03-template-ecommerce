package com.zup.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.ecommerce.controllers.dto.LoginRequest;
import com.zup.ecommerce.controllers.dto.LoginResponse;
import com.zup.ecommerce.security.JwtService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	private AuthenticationManager manager;
	private JwtService jwt;

	public AutenticacaoController(AuthenticationManager manager, JwtService jwt) {
		this.manager = manager;
		this.jwt = jwt;
	}

	@PostMapping
	public ResponseEntity<LoginResponse> logar(@RequestBody @Valid LoginRequest request) {
		try {
			Authentication authentication = manager.authenticate(request.converter());
			String token = jwt.gerarToken(authentication);
			return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
