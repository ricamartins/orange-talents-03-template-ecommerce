package com.zup.ecommerce.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.ecommerce.controllers.dto.OpiniaoRequest;
import com.zup.ecommerce.controllers.dto.ProdutoRequest;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.repositories.OpiniaoRepository;
import com.zup.ecommerce.repositories.ProdutoRepository;
import com.zup.ecommerce.security.UsuarioLogin;
import com.zup.ecommerce.validations.ErrorResponse;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private OpiniaoRepository opiniaoRepository;

	public ProdutoController(ProdutoRepository produtoRepository, OpiniaoRepository opiniaoRepository) {
		this.produtoRepository = produtoRepository;
		this.opiniaoRepository = opiniaoRepository;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		produtoRepository.save(request.convert(usuario.getId()));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/opinioes")
	public ResponseEntity<?> post(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty())
			return ResponseEntity.badRequest()
				.body(new ErrorResponse("produto_id", "Id de produto inexistente"));
		
		opiniaoRepository.save(request.convert(usuario.getId(), produto.get()));
		return ResponseEntity.ok().build();
	}
}
