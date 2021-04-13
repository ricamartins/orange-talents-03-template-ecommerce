package com.zup.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.ecommerce.models.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	Optional<Compra> findByIdExterno(String id);

}
