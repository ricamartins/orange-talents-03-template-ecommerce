package com.zup.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.ecommerce.controllers.dto.ProdutoRequest;
import com.zup.ecommerce.repositories.ProdutoRepository;
import com.zup.ecommerce.security.UsuarioLogin;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository repository;

	public ProdutoController(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		repository.save(request.convert(usuario.getId()));
		return ResponseEntity.ok().build();
	}
	
}
