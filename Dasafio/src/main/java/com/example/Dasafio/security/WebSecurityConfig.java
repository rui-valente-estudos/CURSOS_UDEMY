package com.example.Dasafio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity /// AQui digo que é uma classe de Segurança do WebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	
	
	/**
	 * Metodo que devolve a instancia do objecto que sabe devolver o nosso padrão de condificação
	 * Náo tem nada haver com o JWT.
	 * Este metodo é só para codificar o Token, a password do Utilizador ,da Base de Dados, gerando um HASH
	 * @return
	 */
	// Marcamos como @Bean para poder ser inicializado com @Autowired
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/**
	 * Motodo Padraó para configurar o nosso custom com o nosso metodo de cndificar a password.
	 * SubEscrever o metodo configure() da Class WebSecurityConfigurerAdapter
	 * @throws Exception 
	 */
	@Override
	public void configure(AuthenticationManagerBuilder  authenticationManagerBuilder) throws Exception {		
		authenticationManagerBuilder
		.userDetailsService(customUserDetailsService)
		.passwordEncoder(passwordEncoder());			
	}
	
	// Metopdo padrao. Este metodo é OBRIGATORIO para conseguirmos trabalhar com autenticacao no LOGIN
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {								
		return super.authenticationManagerBean();
	}
	
	// Metodo que tem a configuracao GLOBAL de ACESSOS e PERMISSOES POR URI(ROTAS)
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {				
		
		//Parte padráo da configuração,		
		httpSecurity
		.cors().and().csrf().disable()
		.exceptionHandling()
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		
		// Apartir daqui vamos fazer as nossas validacoes
		// Vamos informar as nossas ROTAS que vao necessitar de autenticacao
		
		// AQui não queremos autenticacao
		.antMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/login").permitAll()
		// TODOS OS OUTROS URI, QUEREMOS AUTENTICACAO
		.anyRequest()
		.authenticated();
				
		//AQui indico que queero usar o filter que criei
		//Aqui indico que ANTES DE QUALQUER REQUISICAO HTTP, o sistema deve usar o meu filtro JWT
		httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
	
	
}
