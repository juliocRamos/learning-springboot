package com.learningspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningspring.entities.Usuario;

/**
 * Repositório de {@class Usuario} utilizando SpringData JPA
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
