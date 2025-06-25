package com.example.Dasafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Cliente;
import com.example.Dasafio.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> obterTodos() {
		return clienteRepository.findAll();
	}

	public Optional<Cliente> obterPorId(Integer id) {
		return clienteRepository.findById(id);
	}

	public Cliente adicionar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente atualizar(Integer id, Cliente cliente) {
		cliente.setId(id);
		return clienteRepository.save(cliente);
	}

	public void eliminar(Integer id) {
		clienteRepository.deleteById(id);
	}

}
