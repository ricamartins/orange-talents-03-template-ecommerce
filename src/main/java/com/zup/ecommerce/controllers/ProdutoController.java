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

import com.zup.ecommerce.components.EmailSender;
import com.zup.ecommerce.controllers.dto.OpiniaoRequest;
import com.zup.ecommerce.controllers.dto.PerguntaRequest;
import com.zup.ecommerce.controllers.dto.PerguntaResponse;
import com.zup.ecommerce.controllers.dto.ProdutoRequest;
import com.zup.ecommerce.models.Pergunta;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.repositories.OpiniaoRepository;
import com.zup.ecommerce.repositories.PerguntaRepository;
import com.zup.ecommerce.repositories.ProdutoRepository;
import com.zup.ecommerce.security.UsuarioLogin;
import com.zup.ecommerce.validations.ErrorResponse;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	private OpiniaoRepository opiniaoRepository;
	private PerguntaRepository perguntaRepository;
	private EmailSender emailSender;
	
	public ProdutoController(ProdutoRepository produtoRepository,
			OpiniaoRepository opiniaoRepository,
			PerguntaRepository perguntaRepository,
			EmailSender emailSender) {
		this.produtoRepository = produtoRepository;
		this.opiniaoRepository = opiniaoRepository;
		this.perguntaRepository = perguntaRepository;
		this.emailSender = emailSender;
	}
	
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		produtoRepository.save(request.convert(usuario.getId()));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/opinioes")
	public ResponseEntity<?> postarOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty())
			return ResponseEntity.badRequest()
				.body(new ErrorResponse("produto_id", "Id de produto inexistente"));
		
		opiniaoRepository.save(request.convert(usuario.getId(), produto.get()));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/perguntas")
	public ResponseEntity<?> criarPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaRequest request, @AuthenticationPrincipal UsuarioLogin usuario) {
		
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isEmpty())
			return ResponseEntity.badRequest()
				.body(new ErrorResponse("produto_id", "Id de produto inexistente"));
		
		Pergunta pergunta = perguntaRepository.save(request.convert(usuario.getId(), produto.get()));
		
		if (!emailSender.send(pergunta))
			return ResponseEntity.badRequest()
					.body(new ErrorResponse("email", "Erro ao enviar o email"));
			
		return ResponseEntity.ok(new PerguntaResponse(pergunta));
	}
}
