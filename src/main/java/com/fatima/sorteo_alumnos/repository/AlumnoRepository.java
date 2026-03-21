package com.fatima.sorteo_alumnos.repository;

import com.fatima.sorteo_alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    List<Alumno> findByCursoIdAndActivoTrue(Long cursoId);
}