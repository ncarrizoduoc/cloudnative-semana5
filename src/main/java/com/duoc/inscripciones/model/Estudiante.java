package com.duoc.inscripciones.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe ingresar el nombre del estudiante")
    @NotBlank(message = "El nombre del estudiante no puede ser un texto vacio")
    @Size(min = 3, max = 200, message = "El nombre del estudiante debe tener entre 3 y 200 caracteres")
    private String nombre;

    @NotNull(message = "Debe ingresar la carrera")
    @NotBlank(message = "La carrera no puede ser un texto vacio")
    @Size(min = 3, max = 200, message = "El nombre de la carrera debe tener entre 3 y 200 caracteres")
    private String carrera;

}
