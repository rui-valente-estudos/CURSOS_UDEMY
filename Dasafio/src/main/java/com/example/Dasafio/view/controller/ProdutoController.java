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

import com.example.Dasafio.model.Produto;
import com.example.Dasafio.service.ProdutoService;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
		
		
		@Autowired
		private ProdutoService produtoService;

		/// Devolver DTO
		@GetMapping
		public List<Produto> obterTodos(){
			return produtoService.obterTodos();
		}
		
		
		@GetMapping("/{id}")
		public Optional<Produto> obterPorId(@PathVariable Integer id){
			return produtoService.obterPorId(id);
		}
		
		@PostMapping
		public Produto adicionar(@RequestBody Produto produto) {
			return produtoService.adicionar(produto);
		}
		
		@PutMapping("/{id}")
		public Produto atualizar(@PathVariable Integer id,  @RequestBody Produto produto) {
			return produtoService.atualizar(id, produto);
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			produtoService.eliminar(id);
		}
		
		
}
		