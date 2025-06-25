package com.example.Dasafio.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Dasafio.model.Usuario;
import com.example.Dasafio.service.UsuarioService;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService; 
	
	@Override
	public UserDetails loadUserByUsername(String email){
//		Usuario usuario = getUser(()-> usuarioService.obterPorEmail(email));
//		return usuario;
		return usuarioService.obterPorEmail(email).get();
	}
	
	public UserDetails obterUsuarioPorId(Long id){
		return usuarioService.obterPorId(id).get();
	}
	
	// Supplier É O TIPO DE VARIAVEL PARA DEFINIR O "lamda, ->", CASO QUEIRAMOS USAR COMO VARIAVEL OU PARAMENTRO
	public Usuario getUser(Supplier<Optional<Usuario>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Utilizador não encotrado"));
	}
}
