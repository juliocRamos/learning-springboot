package com.learningspring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningspring.entities.Usuario;
import com.learningspring.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;

	/**
	 * Endpoint para acessar as informações de {@link Usuario}
	 * 
	 * @return uma lista de Usuários em formato JSON.
	 *   
	 */
	@GetMapping()
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> list = service.getAllEntities();
		return ResponseEntity.ok().body(list);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
		Usuario usuario = service.getEntity(id);
		return ResponseEntity.ok().body(usuario);
	}
}
