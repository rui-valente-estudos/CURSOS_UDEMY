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

import com.example.Dasafio.model.Categoria;
import com.example.Dasafio.service.CategoriaService;


@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {

	
		@Autowired
		private CategoriaService categoriaService;

		/// Devolver DTO
		@GetMapping
		public List<Categoria> obterTodos(){
			return categoriaService.obterTodos();
		}
		
		
		@GetMapping("/{id}")
		public Optional<Categoria> obterPorId(@PathVariable Integer id){
			return categoriaService.obterPorId(id);
		}
		
		@PostMapping
		public Categoria adicionar(@RequestBody Categoria categoria) {
			return categoriaService.adicionar(categoria);
		}
		
		@PutMapping("/{id}")
		public Categoria atualizar(@PathVariable Integer id,  @RequestBody Categoria categoria) {
			return categoriaService.atualizar(id, categoria);
		}
		
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Integer id) {
			categoriaService.eliminar(id);
		}
		
		
}
		