package com.example.Dasafio.view.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Dasafio.model.MensagemEmail;
import com.example.Dasafio.model.Usuario;
import com.example.Dasafio.service.EmailService;
import com.example.Dasafio.service.UsuarioService;
import com.example.Dasafio.view.model.usuario.LoginRequest;
import com.example.Dasafio.view.model.usuario.LoginResponse;


@CrossOrigin("*") // PARA ACEITER REQUISICOES DE QUALQUER APLICACAO
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;
	
	// Devia devolver um DTO
	@GetMapping
	public List<Usuario> obterTodos(){
		return usuarioService.obterTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<Usuario> obterPorId(@PathVariable("id") Long id){
		return usuarioService.obterPorId(id);
	}

	@PostMapping
	public Usuario adicionar(@RequestBody Usuario usuario) {
		return usuarioService.adicionar(usuario);
	}
		
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest loginRequest) {		
		return usuarioService.logar(loginRequest.getEmail(), loginRequest.getSenha());
	}
	
	@PostMapping("/email")
	public String enviarEmail(@RequestBody MensagemEmail email) {
	
		try {
			emailService.enviar(email);
		} catch (MessagingException e) {			
			e.printStackTrace();
			return "ERRO NO ENVIO DO E-MAIL.";
		}
	
		return "Email enviado com sucesso.";
	}
}
