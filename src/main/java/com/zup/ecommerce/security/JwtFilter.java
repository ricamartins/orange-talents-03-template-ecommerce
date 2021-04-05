package com.zup.ecommerce.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zup.ecommerce.models.Usuario;
import com.zup.ecommerce.repositories.UsuarioRepository;

public class JwtFilter extends OncePerRequestFilter {

	private JwtService jwt;

	private UsuarioRepository repository;
	
	public JwtFilter(JwtService jwt, UsuarioRepository repository) {
		this.jwt = jwt;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Optional<String> token = pegarTokenDoHeader(request);

		if (token.isPresent() && jwt.validarToken(token.get()))
			autenticarUsuario(token.get());

		filterChain.doFilter(request, response);
	}

	private Optional<String> pegarTokenDoHeader(HttpServletRequest request) {

		String header = request.getHeader("Authorization");

		if (header == null || header.isBlank() || !header.startsWith("Bearer "))
			return Optional.empty();

		return Optional.of(header.substring(7, header.length()));
	}

	private void autenticarUsuario(String token) {
		Optional<Usuario> usuario = repository.findById(jwt.pegarIdUsuario(token));
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(new UsuarioLogin(usuario.get()), null, usuario.get().getRoles());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
