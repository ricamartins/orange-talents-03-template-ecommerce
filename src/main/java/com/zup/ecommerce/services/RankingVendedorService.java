package com.zup.ecommerce.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zup.ecommerce.components.DisparadorEventoCompra;
import com.zup.ecommerce.models.Compra;

@Service
public class RankingVendedorService implements DisparadorEventoCompra {

	@Override
	public String request(Compra compra) {
		RestTemplate template = new RestTemplate();
		String params = "?idCompra="+compra.getId()+"&idVendedor="+compra.getProduto().getUsuario().getId();
		String url = "http://localhost:8080/servicos/ranking-vendedores"+params;
		ResponseEntity<String> entity = template.getForEntity(url, String.class);
		return entity.getBody();
	}
}
