package com.zup.ecommerce.components;

import org.springframework.stereotype.Component;

import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.Pergunta;
import com.zup.ecommerce.models.Produto;

@Component
public class EmailSender {

	public boolean send(Pergunta pergunta) {
		
		System.out.println("De: ecommerce@mail.com");
		System.out.println("Para: "+pergunta.getProduto().getUsuario().getEmail());
		System.out.println("Titulo: Pergunta enviada por usuário");
		System.out.println("Corpo: "+pergunta.getTitulo());
		System.out.println("       "+pergunta.getCorpo());
		
		return false;
	}

	public boolean send(Compra compra, Produto produto) {

		System.out.println("De: ecommerce@mail.com");
		System.out.println("Para: "+produto.getUsuario().getEmail());
		System.out.println("Titulo: Usuario comprou seu produto");
		System.out.println("Corpo: "+produto.getNome());
		
		return true;
	}
}
