package com.fatima.sorteo_alumnos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre_curso")
    private String nombre;
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
}