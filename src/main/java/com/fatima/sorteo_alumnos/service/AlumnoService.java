package com.fatima.sorteo_alumnos.service;

import com.fatima.sorteo_alumnos.dto.AlumnoDTO;
import com.fatima.sorteo_alumnos.entity.Alumno;
import com.fatima.sorteo_alumnos.repository.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AlumnoService {

    private final AlumnoRepository alumnoRepository;

    public AlumnoService(AlumnoRepository alumnoRepository) {
        this.alumnoRepository = alumnoRepository;
    }

    public AlumnoDTO sortearAlumno(Long cursoId) {

        List<Alumno> alumnos = alumnoRepository.findByCursoIdAndActivoTrue(cursoId);

        if (alumnos.isEmpty()) {
            throw new RuntimeException("No hay alumnos en este curso");
        }

        Alumno alumno = alumnos.get(new Random().nextInt(alumnos.size()));

        // convertir a DTO
        AlumnoDTO dto = new AlumnoDTO();
        dto.setNombre(alumno.getNombre());
        dto.setApellido(alumno.getApellido());

        return dto;
    }
}