package com.zup.ecommerce.controllers.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.GatewayPagamento;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Usuario;
import com.zup.ecommerce.validations.MustExist;
import com.zup.ecommerce.validations.ValidEnum;

public class CompraRequest {

	@NotNull @Positive
	private Integer quantidade;
	@NotNull @MustExist(klass=Produto.class)
	private Long produtoId;
	@ValidEnum(GatewayPagamento.class)
	private String gateway;

	public CompraRequest(@NotNull @Positive Integer quantidade, Long produtoId, String gateway) {
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.gateway = gateway;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Long getProdutoId() {
		return produtoId;
	}
	
	public GatewayPagamento getGateway() {
		return GatewayPagamento.valueOf(gateway);
	}
	
	public Compra convert(Long idUsuario, Produto produto) {		
		Usuario comprador = new Usuario(idUsuario);
		return new Compra(quantidade, produto.getPreco(), produto, comprador, GatewayPagamento.valueOf(gateway));
	}

	@Override
	public String toString() {
		return "CompraRequest [quantidade=" + quantidade + ", produto=" + produtoId + ", gateway=" + gateway + "]";
	}
	
}
