package com.zup.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_categorias")
public class Categoria {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	@ManyToOne
	private Categoria superCategoria;

	@Deprecated
	private Categoria() {}
	
	public Categoria(Long categoriaId) {
		this.id = categoriaId;
	}
	
	public Categoria(String nome, Categoria superCategoria) {
		this.nome = nome;
		this.superCategoria = superCategoria;
	}

	public Long getId() {
		return id;
	}

}
