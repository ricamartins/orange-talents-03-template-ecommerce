package com.zup.ecommerce.controllers.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.zup.ecommerce.models.Produto;

public class ProdutoResponse {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private List<String> imagens;
	private List<CaracteristicaResponse> caracteristicas;
	private Integer quantidadeNotas;
	private Double notaMedia;
	private List<OpiniaoResponse> opinioes;
	private List<PerguntaResponse> perguntas;
	
	public ProdutoResponse(Produto produto) {
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getPreco();
		this.imagens = produto.getImagens().stream().map(i -> i.getLink()).collect(Collectors.toList());
		this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new).collect(Collectors.toList());
		this.opinioes = produto.getOpinioes().stream().map(OpiniaoResponse::new).collect(Collectors.toList());
		this.quantidadeNotas = this.opinioes.size();
		this.notaMedia = this.calcularNotaMedia();
		this.perguntas = produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public List<CaracteristicaResponse> getCaracteristicas() {
		return caracteristicas;
	}

	public Integer getQuantidadeNotas() {
		return quantidadeNotas;
	}

	public Double getNotaMedia() {
		return notaMedia;
	}

	public List<OpiniaoResponse> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaResponse> getPerguntas() {
		return perguntas;
	}
	
	public Double calcularNotaMedia() {
		return opinioes.stream().mapToInt(o -> o.getNota()).average().orElse(0);
	}
}
