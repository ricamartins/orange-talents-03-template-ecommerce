package com.zup.ecommerce.models;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Role> roles;
	
	@Deprecated
	public Usuario() {}
	
	public Usuario(@NotEmpty @Email String email, @NotEmpty @Length(min = 6) String senha,
			@Past LocalDateTime instanteCadastro) {
		this.email = email;
		this.senha = senha;
		this.instanteCadastro = instanteCadastro;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
}
