package com.learningspring.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.learningspring.constants.PedidoStatusConstants;
import com.learningspring.entities.Categoria;
import com.learningspring.entities.ItemPedido;
import com.learningspring.entities.Pagamento;
import com.learningspring.entities.Pedido;
import com.learningspring.entities.Produto;
import com.learningspring.entities.Usuario;
import com.learningspring.repositories.CategoriaRepository;
import com.learningspring.repositories.ItemPedidoRepository;
import com.learningspring.repositories.PedidoRepository;
import com.learningspring.repositories.ProdutoRepository;
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

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario(null, "Jhon", "jhon@gmail.com", "999999-800", "t-800", null);
		Usuario u2 = new Usuario(null, "Konnor", "konnor@gmail.com", "999999-0001", "s@r@konnor", null);

		Categoria c1 = new Categoria(null, "Eletrônicos");
		Categoria c2 = new Categoria(null, "Livros");
		Categoria c3 = new Categoria(null, "Computadores");

		Produto pd1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Produto pd2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Produto pd3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
		Produto pd4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
		Produto pd5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		categoriaRepository.saveAll(Arrays.asList(c1, c2, c3));
		produtoRepository.saveAll(Arrays.asList(pd1, pd2, pd3, pd4, pd5));

		pd1.getCategorias().add(c2);
		pd2.getCategorias().addAll(Arrays.asList(c1, c3));
		pd3.getCategorias().add(c3);
		pd4.getCategorias().add(c3);
		pd5.getCategorias().add(c2);

		produtoRepository.saveAll(Arrays.asList(pd1, pd2, pd3, pd4, pd5));

		Pedido p1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), PedidoStatusConstants.PAGO, u1);
		Pedido p2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatusConstants.AGUARDANDO_PAGAMENTO,
				u2);
		Pedido p3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), PedidoStatusConstants.AGUARDANDO_PAGAMENTO,
				u1);

		usuarioRepository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));

		ItemPedido oi1 = new ItemPedido(p1, pd1, 2, pd1.getPreco());
		ItemPedido oi2 = new ItemPedido(p1, pd3, 1, pd3.getPreco());
		ItemPedido oi3 = new ItemPedido(p2, pd3, 2, pd3.getPreco());
		ItemPedido oi4 = new ItemPedido(p3, pd5, 2, pd5.getPreco());

		itemPedidoRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Pagamento pgto1 = new Pagamento(null, Instant.parse("2019-06-20T21:00:07Z"), p1);
		p1.setPagamento(pgto1);
		
		pedidoRepository.save(p1);
	}
}
