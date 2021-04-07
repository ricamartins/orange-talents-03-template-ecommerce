package com.zup.ecommerce.controllers.dto;

import java.time.LocalDateTime;

import com.zup.ecommerce.models.Pergunta;

public class PerguntaResponse {

	private Long id;
	
	private String titulo;
	
	private String corpo;
	
	private LocalDateTime criadoEm;

	public PerguntaResponse(Pergunta pergunta) {
		this.id = pergunta.getId();
		this.titulo = pergunta.getTitulo();
		this.corpo = pergunta.getCorpo();
		this.criadoEm = pergunta.getCriadoEm();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getCorpo() {
		return corpo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}
	
}
