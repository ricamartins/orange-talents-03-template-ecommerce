package com.zup.ecommerce.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="tb_transacoes")
public class Transacao {

	public enum StatusTransacao {
		SUCESSO, ERRO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String gateway;
	
	@NotBlank
	private String idGateway;
	
	@Enumerated(EnumType.STRING)
	@Column(updatable=false)
	private StatusTransacao status;
	
	@CreationTimestamp
	private LocalDateTime instanteProcessamento;

	@ManyToOne
	private Compra compra;
	
	@Deprecated
	public Transacao() {}

	public Transacao(@NotBlank String gateway, @NotBlank String idGateway, StatusTransacao status, Compra compra) {
		this.gateway = gateway.toUpperCase();
		this.idGateway = idGateway;
		this.status = status;
		this.compra = compra;
	}

	public String getGateway() {
		return gateway;
	}
	
	public StatusTransacao getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((gateway == null) ? 0 : gateway.hashCode());
		result = prime * result + ((idGateway == null) ? 0 : idGateway.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (gateway == null) {
			if (other.gateway != null)
				return false;
		} else if (!gateway.equals(other.gateway))
			return false;
		if (idGateway == null) {
			if (other.idGateway != null)
				return false;
		} else if (!idGateway.equals(other.idGateway))
			return false;
		return true;
	}

}
