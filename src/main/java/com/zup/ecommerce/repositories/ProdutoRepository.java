package com.zup.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zup.ecommerce.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
