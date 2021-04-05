package com.zup.ecommerce.controllers.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.zup.ecommerce.models.Categoria;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Usuario;
import com.zup.ecommerce.validations.MustExist;
import com.zup.ecommerce.validations.UniqueElements;

public class ProdutoRequest {

	@NotBlank
	private String nome;
	
	@NotNull @DecimalMin("0.01")
	private BigDecimal preco;
	
	@NotNull @Min(0)
	private Integer quantidade;
	
	@NotBlank @Length(max=1000)
	private String descricao;
	
	@Size(min=3) @UniqueElements
	private List<CaracteristicaRequest> caracteristicas;
	
	@NotNull @MustExist(klass=Categoria.class)
	private Long categoriaId;

	public ProdutoRequest(@NotBlank String nome, @NotNull @DecimalMin("0.01") BigDecimal preco,
			@NotNull @Min(0) Integer quantidade, @NotNull @Length(max = 1000) String descricao,
			List<CaracteristicaRequest> caracteristicas, @NotNull Long categoriaId) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.categoriaId = categoriaId;
	}

	public Produto convert(Long usuarioId) {
		Categoria categoria = new Categoria(categoriaId);
		Usuario usuario = new Usuario(usuarioId);
		return new Produto(nome, preco, quantidade, descricao, caracteristicas, categoria, usuario);
	}
	
	
}
