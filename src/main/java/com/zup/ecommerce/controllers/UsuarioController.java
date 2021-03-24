package com.zup.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.ecommerce.controllers.dto.UsuarioRequest;
import com.zup.ecommerce.repositories.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private UsuarioRepository repository;
	
	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest request) {
		repository.save(request.converter());
		return ResponseEntity.ok().build();
	}
}
