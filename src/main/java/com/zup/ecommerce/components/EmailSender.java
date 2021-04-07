package com.zup.ecommerce.components;

import org.springframework.stereotype.Component;

import com.zup.ecommerce.models.Pergunta;

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
}
