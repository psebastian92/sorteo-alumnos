package com.fatima.sorteo_alumnos.dto;

import lombok.Data;

@Data
public class AlumnoDTO {
    private String nombre;
    private String apellido;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}