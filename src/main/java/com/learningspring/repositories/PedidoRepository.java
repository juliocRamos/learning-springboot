package com.learningspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningspring.entities.Pedido;

/**
 * Repositório de {@class Pedido}
 */
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	
}
