package com.duoc.inscripciones.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Debe ingresar el nombre del curso")
    @NotBlank(message = "El nombre del curso no puede ser un texto vacio")
    @Size(min = 3, max = 200, message = "El nombre del curso debe tener entre 3 y 200 caracteres")
    private String nombre;

    @NotNull(message = "Debe ingresar el nombre del instructor")
    @NotBlank(message = "El nombre del instructor no puede ser un texto vacio")
    @Size(min = 3, max = 200, message = "El nombre del instructor debe tener entre 3 y 200 caracteres")
    private String instructor;

    @Positive(message = "La duracion del curso (en meses) debe ser un numero entero positivo")
    private int duracion;

    @PositiveOrZero(message = "El costo del curso (en pesos) debe ser un entero igual o mayor a 0")
    private int costo;

}
