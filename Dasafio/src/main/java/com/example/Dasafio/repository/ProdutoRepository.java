package com.example.Dasafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Dasafio.model.Produto;


@Repository
public interface ProdutoRepository  extends JpaRepository<Produto,Integer>{

}
