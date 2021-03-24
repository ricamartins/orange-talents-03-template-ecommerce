package com.zup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.ecommerce.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
