package com.zup.ecommerce.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	private String expiration = "60000";
	private String secret = "bcuasc8239fd324hd9g3s29dv9234bg923gd9243gd92gd9342gd932dgb23cxb29yg2x9bcx";

	public String gerarToken(Authentication authentication) {
		UsuarioLogin usuario = (UsuarioLogin) authentication.getPrincipal();
		Date agora = new Date();
		Date dataExpiracao = new Date(agora.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("Orange Talents Ecommerce")
				.setSubject(usuario.getId().toString())
				.setIssuedAt(agora)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long pegarIdUsuario(String token) {
		String id = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
		return Long.parseLong(id);
	}


}
