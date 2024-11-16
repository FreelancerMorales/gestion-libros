package com.biblioteca.gestion_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biblioteca.gestion_libros.model.LibroModel;

@Repository
public interface LibroRepository extends JpaRepository<LibroModel, Long> {
}
