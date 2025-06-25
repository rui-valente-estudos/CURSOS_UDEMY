package com.example.Dasafio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Pedido;
import com.example.Dasafio.repository.PedidoRepository;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	public List<Pedido> obterTodos(){
		return pedidoRepository.findAll();
	}
	
	
	public Optional<Pedido> obterPorId(Integer id) {
		return pedidoRepository.findById(id);
	}
	
	public Pedido adicionar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	public Pedido atualizar(Integer id, Pedido pedido) {
		pedido.setId(id);		
		return pedidoRepository.save(pedido);
	}
	
	public void eliminar(Integer id) {
		pedidoRepository.deleteById(id);
	}

}
