package com.zup.ecommerce.controllers.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zup.ecommerce.models.Opiniao;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Usuario;

public class OpiniaoRequest {

	@NotNull @Min(1) @Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Size(max=500)
	private String descricao;

	public OpiniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao convert(Long idUsuario, Produto produto) {
		Usuario usuario = new Usuario(idUsuario);
		return new Opiniao(nota, titulo, descricao, usuario, produto);
	}
	
}
