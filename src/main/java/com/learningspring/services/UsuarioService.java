package com.learningspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningspring.entities.Usuario;
import com.learningspring.repositories.UsuarioRepository;

@Service
public class UsuarioService {
		
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> getAllEntities(){
		return repository.findAll();
	}
	
	public Usuario getEntity(Long id) {
		Optional<Usuario> usuarioOptional = repository.findById(id);
		return usuarioOptional.get();
	}
}
