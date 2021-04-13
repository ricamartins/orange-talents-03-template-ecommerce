package com.zup.ecommerce.controllers.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.GatewayPagamento;
import com.zup.ecommerce.models.Transacao;
import com.zup.ecommerce.models.Transacao.StatusTransacao;

public class TransacaoRequest {

	@NotBlank
	private String idGateway;
	@NotBlank
	private String status;
	
	public TransacaoRequest(String idGateway, String status) {
		this.idGateway = idGateway;
		this.status = status;
	}
	
	public Optional<Transacao> convert(String gateway, Compra compra) {
		Optional<StatusTransacao> optStatusTransacao = GatewayPagamento.getStatusTransacao(gateway, status);
		
		if (optStatusTransacao.isEmpty())
			return Optional.empty();
		
		return Optional.of(new Transacao(gateway, idGateway, optStatusTransacao.get(), compra));
	}
	
}
