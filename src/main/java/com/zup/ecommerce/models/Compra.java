package com.zup.ecommerce.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.zup.ecommerce.models.Compra.StatusCompra;

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

	public String getIdExterno() {
		return idExterno;
	}
	
	public Usuario getComprador() {
		return comprador;
	}
	
	public void setStatus(StatusCompra status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Compra [id=" + id + ", idExterno=" + idExterno + ", quantidade=" + quantidade + ", precoAtual="
				+ precoAtual + ", produto=" + produto.getId() + ", comprador=" + comprador.getId() + ", status=" + status + ", gateway="
				+ gateway + "]";
	}
	
}
