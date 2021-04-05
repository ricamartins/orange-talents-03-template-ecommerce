package com.zup.ecommerce.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.zup.ecommerce.controllers.dto.CaracteristicaRequest;

@Entity
@Table(name="tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull @DecimalMin("0.01")
	private BigDecimal preco;
	
	@NotNull @Min(0)
	private Integer quantidade;
	
	@NotNull @Length(max=1000)
	private String descricao;
	
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	
	@OneToMany(mappedBy="produto", cascade=CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas;
	
	@ManyToOne @NotNull
	private Categoria categoria;
	
	@ManyToOne @NotNull
	private Usuario usuario;

	public Produto(@NotNull String nome, @NotNull @DecimalMin("0.01") BigDecimal preco,
			@NotNull @Min(0) Integer quantidade, @NotNull @Length(max = 1000) String descricao,
			List<CaracteristicaRequest> caracteristicas, @NotNull Categoria categoria, @NotNull Usuario usuario) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas.stream()
				.map(c -> new Caracteristica(c.getNome(), c.getDescricao(), this))
				.collect(Collectors.toSet());
		this.categoria = categoria;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}