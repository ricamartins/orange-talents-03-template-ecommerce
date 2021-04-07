package com.zup.ecommerce.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zup.ecommerce.models.Usuario;
import com.zup.ecommerce.repositories.UsuarioRepository;

@Service
public class LoginService implements UserDetailsService {

	private UsuarioRepository repository;

	public LoginService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Usuario usuario = repository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));;
		
		return new UsuarioLogin(usuario);
	}

}
