package com.zup.ecommerce.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zup.ecommerce.models.Role;
import com.zup.ecommerce.models.Usuario;

public class UsuarioLogin implements UserDetails {

	private Long id;
	private String username;
	private String password;
	private List<Role> authorities;

	public UsuarioLogin(Usuario usuario) {
		this.id = usuario.getId();
		this.username = usuario.getEmail();
		this.password = usuario.getSenha();
		this.authorities = usuario.getRoles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Long getId() {
		return id;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
