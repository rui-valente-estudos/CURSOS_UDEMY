package com.example.Dasafio.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;



@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JWTService jwtService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	// Metodo principal onde TODAS as requisicoes "BATEM" antes de chegar a outro
	// endpoint;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// apanho o token da requisicao
		String token = obterToken(request);
		// apanho o id do utilizador que está no tokem
		Optional<Long> id = jwtService.obterIdDoUsuario(token);

		if (id.isPresent()) {
			// Apanha o Utilizador dono do Token pelo id
			UserDetails usuario = customUserDetailsService.obterUsuarioPorId(id.get());
			// Neste ponto verificarmos se utilizador se está autenticvado ou náo.
			// Tambem validamos as permissoes
			UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,
					Collections.emptyList());

			// mudando a autenticacao para a propria requisicao
			autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			//// Repasso a autenticacao para o contexto do Security
			///// Apartir daqui, agora, o SPRING ""toma conta"" de todo o resto .
			SecurityContextHolder.getContext().setAuthentication(autenticacao);
		}
		
		// Metodo Padrao para filtrar as regras du Utilizador
		filterChain.doFilter(request, response);
	}

	private String obterToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (!StringUtils.hasText(token)) {
			return null;
		}
		// obter o tokem apartir da 7 posicao
		return token.substring(7);
	}

}
