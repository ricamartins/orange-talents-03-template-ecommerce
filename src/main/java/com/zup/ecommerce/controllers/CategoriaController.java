package com.zup.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.ecommerce.controllers.dto.CategoriaRequest;
import com.zup.ecommerce.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	private CategoriaRepository repository;

	public CategoriaController(CategoriaRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest request) {
		repository.save(request.convert());
		return ResponseEntity.ok().build();
	}
}
