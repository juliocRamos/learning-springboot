package com.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningspring.entities.Categoria;
import com.learningspring.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public List<Categoria> getAllEntities() {
		return repository.findAll();
	}

	public Categoria getEntity(Long id) {
		Optional<Categoria> usuarioOptional = repository.findById(id);
		return usuarioOptional.get();
	}
}