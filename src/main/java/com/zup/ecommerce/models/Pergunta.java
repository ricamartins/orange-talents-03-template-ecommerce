package com.zup.ecommerce.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tb_perguntas")
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String corpo;
	
	@CreationTimestamp
	private LocalDateTime criadoEm;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario usuario;

	public Pergunta(String titulo, String corpo, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.corpo = corpo;
		this.produto = produto;
		this.usuario = usuario;
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
	
	public Produto getProduto() {
		return produto;
	}
	
}
