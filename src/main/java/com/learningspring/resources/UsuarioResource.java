package com.learningspring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario entity){
		entity = service.persistUsuario(entity);
		
		// Cria o uri do recurso para a nova entidade criada
		// com esta abordagem é possível retornar um código 201 CREATED.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).body(entity);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id){
		service.deleteUsuario(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario entity){
		entity = service.updateUsuario(id, entity);
		return ResponseEntity.ok().body(entity);
	}
}
