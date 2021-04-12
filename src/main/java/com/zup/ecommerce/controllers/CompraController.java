package com.zup.ecommerce.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.ecommerce.components.EmailSender;
import com.zup.ecommerce.controllers.dto.CompraRequest;
import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.repositories.CompraRepository;
import com.zup.ecommerce.repositories.ProdutoRepository;
import com.zup.ecommerce.security.UsuarioLogin;
import com.zup.ecommerce.validations.ErrorResponse;

@RestController
@RequestMapping("/compras")
public class CompraController {

	private CompraRepository compraRepository;
	private ProdutoRepository produtoRepository;
	private EmailSender emailSender;

	public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository, EmailSender emailSender) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.emailSender = emailSender;
	}
	
	@PostMapping
	public ResponseEntity<?> efetuarCompra(@RequestBody @Valid CompraRequest request, @AuthenticationPrincipal UsuarioLogin usuario, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoRepository.findById(request.getProdutoId()).get();

		if (!produto.abaterEstoque(request.getQuantidade(), produtoRepository))
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new ErrorResponse("quantidade", "Quantidade indisponível"));
		
		Compra compra = request.convert(usuario.getId(), produto);

		String redirectUrl = uriBuilder.path("/compras/{id}").buildAndExpand(compra.getIdExterno()).toString();
		
		String locationHeaderUrl = request.getGateway().efetuarCompra(compra, redirectUrl);
		
		compraRepository.save(compra);
		
		emailSender.send(compra, produto);
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.header("Location", locationHeaderUrl)
				.build();
	}
	
}
