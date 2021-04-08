package com.zup.ecommerce.controllers.dto;

import com.zup.ecommerce.models.Opiniao;

public class OpiniaoResponse {

	private Integer nota;
	
	private String titulo;
	
	private String descricao;

	public OpiniaoResponse(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

}
