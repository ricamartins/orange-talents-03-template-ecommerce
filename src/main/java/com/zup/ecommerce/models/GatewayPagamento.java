package com.zup.ecommerce.models;

import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.constraints.NotBlank;

import com.zup.ecommerce.models.Transacao.StatusTransacao;

public enum GatewayPagamento {
	PAYPAL {
		@Override
		public String gerarLink(String id, String redirectUrl) {
			return String.format("paypal.com?buyerId=%s&redirectUrl=%s", id, redirectUrl+"paypal");
		}

		@Override
		public StatusTransacao convertStatus(@NotBlank String status) {
			switch (status.toUpperCase()) {
				case "1":
					return StatusTransacao.SUCESSO;
				case "0":
					return StatusTransacao.ERRO;
				default:
					return null;
			}
		}
	},
	PAGSEGURO {
		@Override
		public String gerarLink(String id, String redirectUrl) {
			return String.format("pagseguro.com?returnId=%s&redirectUrl=%s", id, redirectUrl+"pagseguro");
		}

		@Override
		public StatusTransacao convertStatus(@NotBlank String status) {
			switch (status.toUpperCase()) {
				case "SUCESSO":
					return StatusTransacao.SUCESSO;
				case "ERRO":
					return StatusTransacao.ERRO;
				default:
					return null;
			}
		}
	};
	

	public static Optional<StatusTransacao> getStatusTransacao(String gateway, @NotBlank String status) {
	
		if (!Stream.of(values()).anyMatch(v -> v.name().equals(gateway.toUpperCase())))
			return Optional.empty();
		
		return Optional.ofNullable(valueOf(gateway.toUpperCase()).convertStatus(status));
	}

	public abstract String gerarLink(String id, String redirectUrl);
	public abstract StatusTransacao convertStatus(@NotBlank String status);
}
