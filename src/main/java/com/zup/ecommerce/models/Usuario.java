package com.zup.ecommerce.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="tb_usuarios")
public class Usuario {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty @Email
	private String email;
	
	@NotEmpty @Length(min=6)
	private String senha;

	@Past
	private LocalDateTime instanteCadastro;
	
	@Deprecated
	public Usuario() {}
	
	public Usuario(@NotEmpty @Email String email, @NotEmpty @Length(min = 6) String senha,
			@Past LocalDateTime instanteCadastro) {
		this.email = email;
		this.senha = senha;
		this.instanteCadastro = instanteCadastro;
	}
	
}
