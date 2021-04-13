package com.zup.ecommerce.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zup.ecommerce.components.DisparadorEventoCompra;
import com.zup.ecommerce.models.Compra;

@Service
public class NotaFiscalService implements DisparadorEventoCompra {

	@Override
	public String request(Compra compra) {
		RestTemplate template = new RestTemplate();
		String params = "?idCompra="+compra.getId()+"&idComprador="+compra.getComprador().getId();
		String url = "http://localhost:8080/servicos/nota-fiscal"+params;
		ResponseEntity<String> entity = template.getForEntity(url, String.class);
		return entity.getBody();
	}
}
