package com.zup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.ecommerce.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
