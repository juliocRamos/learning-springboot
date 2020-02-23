package com.learningspring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.learningspring.constants.PedidoStatusConstants;
import com.learningspring.entities.Pedido;
import com.learningspring.entities.Usuario;
import com.learningspring.repositories.PedidoRepository;
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
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Jhon", "jhon@gmail.com", "999999-800", "t-800", null);
		Usuario u2 = new Usuario(null, "Konnor", "konnor@gmail.com", "999999-0001", "s@r@konnor", null);
		
		Pedido p1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), PedidoStatusConstants.PAGO, u1);
		Pedido p2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatusConstants.AGUARDANDO_PAGAMENTO, u2);
		Pedido p3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), PedidoStatusConstants.AGUARDANDO_PAGAMENTO, u1);
		
		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
