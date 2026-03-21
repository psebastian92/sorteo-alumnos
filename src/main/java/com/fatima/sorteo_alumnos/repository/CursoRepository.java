package com.fatima.sorteo_alumnos.repository;

import com.fatima.sorteo_alumnos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}