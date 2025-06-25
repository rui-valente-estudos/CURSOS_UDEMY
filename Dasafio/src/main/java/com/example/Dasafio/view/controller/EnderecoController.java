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

import com.example.Dasafio.model.Endereco;
import com.example.Dasafio.service.EnderecoService;


@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;

	/// Devolver DTO
	@GetMapping
	public List<Endereco> obterTodos(){
		return enderecoService.obterTodos();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Endereco> obterPorId(@PathVariable Integer id){
		return enderecoService.obterPorId(id);
	}
	
	@PostMapping
	public Endereco adicionar(@RequestBody Endereco endereco) {
		return enderecoService.adicionar(endereco);
	}
	
	@PutMapping("/{id}")
	public Endereco atualizar(@PathVariable Integer id,  @RequestBody Endereco endereco) {
		endereco.setId(id);
		return enderecoService.atualizar(id, endereco);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		enderecoService.eliminar(id);
	}
	
	
	
}
