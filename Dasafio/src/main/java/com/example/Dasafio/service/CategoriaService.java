package com.example.Dasafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Categoria;
import com.example.Dasafio.repository.CategoriaRepository;



@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List<Categoria> obterTodos(){
		return categoriaRepository.findAll();
	}
	
	
	public Optional<Categoria> obterPorId(Integer id) {
		return categoriaRepository.findById(id);
	}
	
	public Categoria adicionar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria atualizar(Integer id, Categoria categoria) {
		categoria.setId(id);		
		return categoriaRepository.save(categoria);
	}
	
	public void eliminar(Integer id) {
		categoriaRepository.deleteById(id);
	}

}
