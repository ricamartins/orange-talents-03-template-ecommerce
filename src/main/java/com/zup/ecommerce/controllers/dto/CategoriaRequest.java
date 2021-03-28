package com.zup.ecommerce.controllers.dto;

import com.zup.ecommerce.validations.Unique;

import javax.validation.constraints.NotBlank;

import com.zup.ecommerce.models.Categoria;

public class CategoriaRequest {

	@Unique(clazz=Categoria.class, field="nome")
	@NotBlank
	private String nome;
	private Long superCategoriaId;
	
	public CategoriaRequest(String nome, Long superCategoriaId) {
		this.nome = nome;
		this.superCategoriaId = superCategoriaId;
	}

	public Categoria convert() {
		Categoria superCategoria = superCategoriaId != null ? new Categoria(superCategoriaId) : null;
		return new Categoria(nome, superCategoria);
	}

}
