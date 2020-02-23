package com.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningspring.entities.Produto;
import com.learningspring.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> getAllEntities(){
		return repository.findAll();
	}
	
	public Produto getEntity(Long id) {
		Optional<Produto> entity = repository.findById(id);
		return entity.get();
	}
}
