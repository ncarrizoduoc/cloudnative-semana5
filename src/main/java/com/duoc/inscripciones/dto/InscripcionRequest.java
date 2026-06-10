package com.duoc.inscripciones.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscripcionRequest {

    @PositiveOrZero(message = "El ID de inscripcion debe ser mayor o igual a 0")
    private Long id;

    @NotNull(message = "Debe ingresar un ID de estudiante")
    private int estudiante;

    private Long[] cursos;

}
