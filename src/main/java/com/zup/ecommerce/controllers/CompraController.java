package com.zup.ecommerce.controllers;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.zup.ecommerce.components.DisparadorEventoCompra;
import com.zup.ecommerce.components.EmailSender;
import com.zup.ecommerce.controllers.dto.CompraRequest;
import com.zup.ecommerce.controllers.dto.TransacaoRequest;
import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Transacao;
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
	private Set<DisparadorEventoCompra> disparadores;

	public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository,
							EmailSender emailSender, Set<DisparadorEventoCompra> disparadores) {
		this.compraRepository = compraRepository;
		this.produtoRepository = produtoRepository;
		this.emailSender = emailSender;
		this.disparadores = disparadores;
	}
	
	@PostMapping
	public ResponseEntity<?> efetuarCompra(@RequestBody @Valid CompraRequest request, @AuthenticationPrincipal UsuarioLogin usuario, UriComponentsBuilder uriBuilder) {
		Produto produto = produtoRepository.findById(request.getProdutoId()).get();

		if (!produto.abaterEstoque(request.getQuantidade(), produtoRepository))
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
					.body(new ErrorResponse("quantidade", "Quantidade indisponível"));
		
		Compra compra = request.convert(usuario.getId(), produto);

		String redirectUrl = uriBuilder.path("/compras/{id}/transacoes?gateway=").buildAndExpand(compra.getIdExterno()).toString();
		
		String locationHeaderUrl = compra.efetuar(redirectUrl);
		
		compraRepository.save(compra);
		
		emailSender.send(compra, produto);
		
		System.out.println(locationHeaderUrl);
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.header("Location", locationHeaderUrl)
				.build();
	}

	@PostMapping("/{id}/transacoes")
	public ResponseEntity<?> efetuarTransacao(@PathVariable String id, String gateway, @RequestBody @Valid TransacaoRequest request) {
		
		Optional<Compra> optCompra = compraRepository.findByIdExterno(id);
		
		if (optCompra.isEmpty() || optCompra.get().isFinalizada())
			return ResponseEntity.badRequest().body(new ErrorResponse("idCompra", "Compra inválida"));

		Compra compra = optCompra.get();
		
		Optional<Transacao> optTransacao = request.convert(gateway, compra);
		
		if (optTransacao.isEmpty())
			return ResponseEntity.badRequest().body(new ErrorResponse("gatewayInfo", "Gateway ou status do gateway inválidos"));
		
		boolean falha = !compra.processarTransacao(optTransacao.get());
		compraRepository.save(compra);

		if (falha) {
			emailSender.send(compra, optTransacao.get());
			return ResponseEntity.badRequest().build();
		}
		
		emailSender.send(compra, optTransacao.get());
		
		disparadores.stream().map(d -> d.request(compra))
					.forEach(System.out::println);
		
		return ResponseEntity.ok().build();
	}
	
}
