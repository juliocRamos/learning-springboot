package com.learningspring.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.learningspring.entities.Usuario;
import com.learningspring.repositories.UsuarioRepository;
import com.learningspring.services.exceptions.DatabaseException;
import com.learningspring.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> getAllEntities() {
		return repository.findAll();
	}

	public Usuario getEntity(Long id) {
		Optional<Usuario> usuarioOptional = repository.findById(id);
		return usuarioOptional.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Usuario persistUsuario(Usuario entity) {
		return repository.save(entity);
	}

	public void deleteUsuario(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

	public Usuario updateUsuario(Long id, Usuario obj) {
		try {
			Usuario entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Usuario entity, Usuario obj) {
		if (entity != null && obj != null) {
			entity.setNome(obj.getNome());
			entity.setEmail(obj.getEmail());
			entity.setTelefone(obj.getTelefone());
		}
	}
}
