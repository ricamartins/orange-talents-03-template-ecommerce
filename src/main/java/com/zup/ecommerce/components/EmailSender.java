package com.zup.ecommerce.components;

import org.springframework.stereotype.Component;

import com.zup.ecommerce.models.Compra;
import com.zup.ecommerce.models.Pergunta;
import com.zup.ecommerce.models.Produto;
import com.zup.ecommerce.models.Transacao;
import com.zup.ecommerce.models.Transacao.StatusTransacao;

@Component
public class EmailSender {

	public boolean send(Pergunta pergunta) {
		
		System.out.println("De: ecommerce@mail.com");
		System.out.println("Para: "+pergunta.getProduto().getUsuario().getEmail());
		System.out.println("Titulo: Pergunta enviada por usuário");
		System.out.println("Corpo: "+pergunta.getTitulo());
		System.out.println("       "+pergunta.getCorpo());
		
		return true;
	}

	public boolean send(Compra compra, Produto produto) {

		System.out.println("De: ecommerce@mail.com");
		System.out.println("Para: "+produto.getUsuario().getEmail());
		System.out.println("Titulo: Usuario comprou seu produto");
		System.out.println("Corpo: "+produto.getNome());
		
		return true;
	}

	public boolean send(Compra compra, Transacao transacao) {
		
		if (transacao.getStatus().equals(StatusTransacao.SUCESSO)) {
			System.out.println("De: ecommerce@mail.com");
			System.out.println("Para: "+compra.getComprador().getEmail());
			System.out.println("Titulo: Pagamento feito com sucesso");
			System.out.println("Corpo: Seu produto chegará em breve");
		} else {
			System.out.println("De: ecommerce@mail.com");
			System.out.println("Para: "+compra.getComprador().getEmail());
			System.out.println("Titulo: Erro no processamento do pagamento");
			System.out.println("Corpo: Modo de pagamento "+transacao.getGateway());
			System.out.println("       Tente outra vez");			
		}
		
		return true;
	}
}
