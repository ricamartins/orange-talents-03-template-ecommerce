package com.zup.ecommerce.models;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.ecommerce.models.Transacao.StatusTransacao;

@Entity
@Table(name="tb_compras")
public class Compra {

	public enum StatusCompra {
		INICIADA, ANDAMENTO, FINALIZADA;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String idExterno;
	
	@NotNull @Positive
	private Integer quantidade;
	
	@NotNull @Positive
	private BigDecimal precoAtual;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario comprador;
	
	@Enumerated(EnumType.STRING)
	private StatusCompra status;
	
	@Enumerated(EnumType.STRING)
	private GatewayPagamento gateway;

	@OneToMany(mappedBy="compra", cascade=CascadeType.ALL)
	private Set<Transacao> transacoes;
	
	@Deprecated
	public Compra() {}
	
	public Compra(@NotNull @Positive Integer quantidade, @NotNull @Positive BigDecimal precoAtual, Produto produto,
			Usuario comprador, GatewayPagamento gateway) {
		this.idExterno = UUID.randomUUID().toString().replace("-", "");
		this.quantidade = quantidade;
		this.precoAtual = precoAtual;
		this.produto = produto;
		this.comprador = comprador;
		this.status = StatusCompra.INICIADA;
		this.gateway = gateway;
	}

	public Long getId() {
		return id;
	}
	
	public String getIdExterno() {
		return idExterno;
	}
	
	public Usuario getComprador() {
		return comprador;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public String efetuar(String redirectUrl) {
		status = StatusCompra.ANDAMENTO;
		return gateway.gerarLink(idExterno, redirectUrl);
	}

	public boolean isFinalizada() {
		return status == StatusCompra.FINALIZADA;
	}
	
	public boolean processarTransacao(Transacao transacao) {
		
		if (!transacoes.add(transacao) || transacao.getStatus().equals(StatusTransacao.ERRO))
			return false;
		
		status = StatusCompra.FINALIZADA;
		
		return true;
	}
	
}
