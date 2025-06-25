package com.example.Dasafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Dasafio.model.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

}
