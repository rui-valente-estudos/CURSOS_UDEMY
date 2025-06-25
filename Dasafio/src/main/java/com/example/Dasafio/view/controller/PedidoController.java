package com.example.Dasafio.view.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dasafio.model.Pedido;
import com.example.Dasafio.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	/// Devolver DTO
	@GetMapping
	public List<Pedido> obterTodos(){
		return pedidoService.obterTodos();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Pedido> obterPorId(@PathVariable Integer id){
		return pedidoService.obterPorId(id);
	}
	
	@PostMapping
	public Pedido adicionar(@RequestBody Pedido pedido) {
		return pedidoService.adicionar(pedido);
	}
	
	@PutMapping("/{id}")
	public Pedido atualizar(@PathVariable Integer id,  @RequestBody Pedido pedido) {
		pedido.setId(id);
		return pedidoService.atualizar(id, pedido);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		pedidoService.eliminar(id);
	}
	
	
}
