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
	
	public Usuario persistUsuario(Usuario entity) {
		return repository.save(entity);
	}
	
	public void deleteUsuario(Long id) {
		repository.deleteById(id);
	}
	
	public Usuario updateUsuario(Long id, Usuario obj) {
		Usuario entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Usuario entity, Usuario obj) {
		if (entity != null && obj != null) {
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			entity.setTelefone(obj.getTelefone());
		}
	}
}
