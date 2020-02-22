package com.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningspring.entities.Pedido;
import com.learningspring.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public List<Pedido> getAllEntities(){
		return repository.findAll();
	}
	
	public Pedido getEntity(Long id) {
		Optional<Pedido> entity = repository.findById(id);
		return entity.get();
	}
}
