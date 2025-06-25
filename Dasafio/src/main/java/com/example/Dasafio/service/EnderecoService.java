package com.example.Dasafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Endereco;
import com.example.Dasafio.repository.EnderecoRepository;


@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public List<Endereco> obterTodos(){
		return enderecoRepository.findAll();
	}
	
	
	public Optional<Endereco> obterPorId(Integer id) {
		return enderecoRepository.findById(id);
	}
	
	public Endereco adicionar(Endereco evento) {
		return enderecoRepository.save(evento);
	}

	public Endereco atualizar(Integer id, Endereco endereco) {
		endereco.setId(id);		
		return enderecoRepository.save(endereco);
	}
	
	public void eliminar(Integer id) {
		enderecoRepository.deleteById(id);
	}
	
}
