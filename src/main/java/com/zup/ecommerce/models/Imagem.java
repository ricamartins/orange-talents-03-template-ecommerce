package com.zup.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_imagens")
public class Imagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String link;
	private String nome;
	private Long tamanho;
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Imagem() {}

	public Imagem(String link, String nome, Long tamanho, Produto produto) {
		this.link = link;
		this.nome = nome;
		this.tamanho = tamanho;
		this.produto = produto;
	}
	
	public String getLink() {
		return link;
	}
}
