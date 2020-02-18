package com.learningspring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.learningspring.entities.Usuario;
import com.learningspring.repositories.UsuarioRepository;

/**
 * Classe de configuração para o perfil de teste, definido em
 * application.properties.
 * 
 * Utilizado para inicializar o H2 e inserir registros de teste com informações
 * mockadas.
 *
 */
@Configuration
@Profile("teste")
public class TesteConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Usuario u1 = new Usuario(null, "Jhon", "jhon@gmail.com", "999999-800", "t-800");
		Usuario u2 = new Usuario(null, "Konnor", "konnor@gmail.com", "999999-0001", "s@r@konnor");
		
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}
