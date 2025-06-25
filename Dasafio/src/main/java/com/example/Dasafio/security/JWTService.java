package com.example.Dasafio.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.Dasafio.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTService {
	
	//Exemplo de Chave secreta utilizada pelo JWT para codificar e descodificar o Token
	private static final String chavePrivadaJWT = "secretKey";
	
	/**
	 * Metodo para gerar o TOKEN JWT
	 * @param authentication , Autenticacao do Utilizador
	 * @return Token
	 */
	public String gerarToken(Authentication authentication) {
		int tempoExpiracao = 86400000;// 1Dias, 24Horas em milisegundos
		
		//Data de Expiracao para o Token com base no tempo de Expiracao
		//Data atual mais 1 dia em milisegudos
		Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		//Aqui junta os dados gera um tokem (em JWT) e devolve o Token
		return Jwts.builder()
				.setSubject(usuario.getId().toString())
				.setIssuedAt(new Date())
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS512, chavePrivadaJWT).compact();
	}
	
	
	/**
	 * Metodo para deolver o is do utilizador dono do token
	 * @param token do utilizador
	 * @return id do utilizador
	 */
	public Optional<Long> obterIdDoUsuario(String token){
		
		try {
			//devolve as permissoes do token
			Claims claims = parse(token).getBody();
			
			// devolve o id(Long)  dentro do token, se encontrar
			return Optional.ofNullable(Long.parseLong(claims.getSubject()));
		}catch(Exception e) {
			// se náo encontrar nada, devolve null
			return Optional.empty();
		}
	}


	//Metodo para descubrir dentro do token com base na chave privada principal qual as permissoes do utilizador
	private Jws<Claims> parse(String token) {
		return Jwts.parser().setSigningKey(chavePrivadaJWT).parseClaimsJws(token);
	}
	
	
	
	
}
