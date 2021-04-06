package com.zup.ecommerce.components;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zup.ecommerce.models.Imagem;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.repositories.ImagemRepository;

@Component
public class ImageUploader {

	private String folder = Paths.get("").toAbsolutePath().toString().concat("\\src\\main\\resources\\static\\");

	private ImagemRepository repository;

	public ImageUploader(ImagemRepository repository) {
		this.repository = repository;
	}
	
	public boolean upload(MultipartFile[] files, Produto p) {
		List<Imagem> imagens = Stream.of(files).map(f -> upload(f, p)).collect(Collectors.toList());
		
		if (imagens.stream().anyMatch(i -> i == null))
			return false;
		
		repository.saveAll(imagens);
		return true;
	}
	
	private Imagem upload(MultipartFile file, Produto produto) {
		
		Path path = Paths.get(folder+file.getOriginalFilename());
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return criarImagem(file, produto);
	}

	private Imagem criarImagem(MultipartFile file, Produto produto) {
		String nome = file.getOriginalFilename();
		Long tamanho = file.getSize();
		String link = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path(file.getOriginalFilename()).toUriString();

		return new Imagem(link, nome, tamanho, produto);
	}
	
}
