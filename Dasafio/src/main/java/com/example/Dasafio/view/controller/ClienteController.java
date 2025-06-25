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

import com.example.Dasafio.model.Cliente;
import com.example.Dasafio.service.ClienteService;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;

	/// Devolver DTO
	@GetMapping
	public List<Cliente> obterTodos(){
		return clienteService.obterTodos();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Cliente> obterPorId(@PathVariable Integer id){
		return clienteService.obterPorId(id);
	}
	
	@PostMapping
	public Cliente adicionar(@RequestBody Cliente cliente) {
		return clienteService.adicionar(cliente);
	}
	
	@PutMapping("/{id}")
	public Cliente atualizar(@PathVariable Integer id,  @RequestBody Cliente cliente) {
		return clienteService.atualizar(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		clienteService.eliminar(id);
	}
	
	
}
	