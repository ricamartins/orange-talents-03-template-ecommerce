package com.zup.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_opinioes")
public class Opiniao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull @Min(1) @Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank @Size(max=500)
	private String descricao;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;
	
	@Deprecated
	public Opiniao() {}

	public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
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
