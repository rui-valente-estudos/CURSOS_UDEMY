package com.example.Dasafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Dasafio.model.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

}
