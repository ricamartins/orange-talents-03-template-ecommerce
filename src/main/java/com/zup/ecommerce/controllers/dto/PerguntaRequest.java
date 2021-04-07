package com.zup.ecommerce.controllers.dto;

import javax.validation.constraints.NotBlank;

import com.zup.ecommerce.models.Pergunta;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	public PerguntaRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}

	@NotBlank
	private String corpo;

	public PerguntaRequest(@NotBlank String titulo, @NotBlank String corpo) {
		this.titulo = titulo;
		this.corpo = corpo;
	}

	public Pergunta convert(Long usuarioId, Produto produto) {
		Usuario usuario = new Usuario(usuarioId);
		return new Pergunta(titulo, corpo, produto, usuario);
	}
	
}
