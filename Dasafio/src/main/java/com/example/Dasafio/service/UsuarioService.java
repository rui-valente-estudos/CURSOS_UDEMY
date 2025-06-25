package com.example.Dasafio.service;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Usuario;
import com.example.Dasafio.repository.UsuarioRepository;
import com.example.Dasafio.security.JWTService;
import com.example.Dasafio.view.model.usuario.LoginResponse;


@Service
public class UsuarioService {
	
	
	private static final String hederPrefix = "Bearer ";

	@Autowired
	private UsuarioRepository  usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	
	/// USAMOS PARA PERMITIR TER MAIS DE UM END_POINT
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	public List<Usuario> obterTodos(){
		return usuarioRepository.findAll();
	}
	
	public Optional<Usuario> obterPorId(Long id){
		return usuarioRepository.findById(id);
	}
	
	
	public Optional<Usuario> obterPorEmail(String email){
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario adicionar(Usuario usuario) {		
		usuario.setId(null);
		if(obterPorEmail(usuario.getEmail()).isPresent()){
			// Aqui poderia lancar uma exception, como o utilizador ja exiete
			throw new InputMismatchException("Utilizador com o Email:"+usuario.getEmail()+", ja existe");
		}
		// Aqui codifica a senha, gerando um Hash
		String senha = passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(senha);
		
		usuarioRepository.save(usuario);
		
		return usuario;
	}
	
	
	public LoginResponse logar(String email, String password) {
		
		// efetua a AUTENTICACAO 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password, Collections.emptyList()));
		
		// passo a nova autenticacao para o Spring
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// gerar o token do utilizador para devolver 
		String token = hederPrefix + jwtService.gerarToken(authentication);
		//token = Bearer asas3232ddd
		
		Usuario usuario = usuarioRepository.findByEmail(email).get();
		
		return new LoginResponse(token, usuario);		
	}
	
	
}
