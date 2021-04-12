package com.zup.ecommerce.models;

import com.zup.ecommerce.models.Compra.StatusCompra;

public enum GatewayPagamento {
	PAYPAL {
		@Override
		public String efetuarCompra(Compra compra, String redirectUrl) {
			compra.setStatus(StatusCompra.ANDAMENTO);
			return String.format("paypal.com?buyerId=%s&redirectUrl=%s", compra.getIdExterno(), redirectUrl);
		}
	},
	PAGSEGURO {
		@Override
		public String efetuarCompra(Compra compra, String redirectUrl) {
			compra.setStatus(StatusCompra.ANDAMENTO);
			return String.format("pagseguro.com?returnId=%s&redirectUrl=%s", compra.getIdExterno(), redirectUrl);
		}
	};
	
	public abstract String efetuarCompra(Compra compra, String redirectUrl);
}
