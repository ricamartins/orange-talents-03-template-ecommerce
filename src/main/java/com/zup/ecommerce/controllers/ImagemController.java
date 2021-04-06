package com.zup.ecommerce.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zup.ecommerce.components.ImageUploader;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.repositories.ProdutoRepository;
import com.zup.ecommerce.security.UsuarioLogin;
import com.zup.ecommerce.validations.FileValidator;

@RestController
@RequestMapping("/produtos")
public class ImagemController {

	private ImageUploader uploader;
	private ProdutoRepository repository;

	public ImagemController(ImageUploader uploader, ProdutoRepository repository) {
		this.uploader = uploader;
		this.repository = repository;
	}

	@InitBinder("files")
	public void init(WebDataBinder binder) {
		binder.addValidators(new FileValidator());
	}
	
	
	@PostMapping("/{id}/imagens")
	public ResponseEntity<?> cadastrar(@RequestPart("file") MultipartFile[] files, @PathVariable Long id, @AuthenticationPrincipal UsuarioLogin usuario) {

		Optional<Produto> produto = repository.findById(id);
		
		if (produto.isEmpty())
			return ResponseEntity.badRequest().build();
		
		if (produto.get().getUsuario().getId() != usuario.getId())
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		
		if (!uploader.upload(files, produto.get()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar uma das imagens");
		
		return ResponseEntity.ok().build();
	}
}
