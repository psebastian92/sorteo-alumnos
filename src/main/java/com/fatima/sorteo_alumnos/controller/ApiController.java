package com.fatima.sorteo_alumnos.controller;

import com.fatima.sorteo_alumnos.dto.AlumnoDTO;
import com.fatima.sorteo_alumnos.entity.Curso;
import com.fatima.sorteo_alumnos.repository.CursoRepository;
import com.fatima.sorteo_alumnos.service.AlumnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    private final AlumnoService alumnoService;
    private final CursoRepository cursoRepository;

    public ApiController(AlumnoService alumnoService, CursoRepository cursoRepository) {
        this.alumnoService = alumnoService;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/cursos")
    public List<Curso> obtenerCursos() {
        return cursoRepository.findAll();
    }

    @GetMapping("/sorteo/{cursoId}")
    public AlumnoDTO sortear(@PathVariable Long cursoId) {
        return alumnoService.sortearAlumno(cursoId);
    }
}