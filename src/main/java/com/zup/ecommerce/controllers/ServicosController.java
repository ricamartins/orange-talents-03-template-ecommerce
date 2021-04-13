package com.zup.ecommerce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/servicos")
public class ServicosController {

	@GetMapping("/nota-fiscal")
	public String gerarNotaFiscal(String idCompra, String idComprador) {
		return "Nota fiscal da compra de ID: "+idCompra+" para o usuário de ID: "+idComprador;
	}
	
	@GetMapping("/ranking-vendedores")
	public String atualizarRankingVendedores(String idCompra, String idVendedor) {
		return "Ranking de vendedores atualizado com venda de ID: "+idCompra+" do vendedor de ID: "+idVendedor;
	}
}
