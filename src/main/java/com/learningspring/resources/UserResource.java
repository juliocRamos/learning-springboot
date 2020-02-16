package com.learningspring.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningspring.entities.Usuario;

@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

	/**
	 * Endpoint para acessar as informações de {@link Usuario}
	 * 
	 * @return
	 *   
	 */
	@GetMapping()
	public ResponseEntity<Usuario> findAll(){
		Usuario usuario = new Usuario(1L, "Teste", "teste@teste.com", "999999999", "123456");
		return ResponseEntity.ok().body(usuario);
		
	}
}
