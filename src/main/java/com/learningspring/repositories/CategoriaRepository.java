package com.learningspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learningspring.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
