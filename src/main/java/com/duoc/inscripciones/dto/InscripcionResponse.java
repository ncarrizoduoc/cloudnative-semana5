package com.duoc.inscripciones.dto;

import java.io.Serializable;
import java.util.List;

import com.duoc.inscripciones.model.Curso;
import com.duoc.inscripciones.model.Estudiante;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionResponse implements Serializable {

    private Long id;
    private Estudiante estudiante;
    private List<Curso> cursos;
    private int total;

    @Override
    public String toString(){
        String string = "";
        string += "ID de inscripcion: " + id + "\n";
        string += "Estudiante:\n";
        string += "\tID: "+ estudiante.getId() + "\n"
                + "\tNombre: " + estudiante.getNombre() + "\n"
                + "\tCarrera: " + estudiante.getCarrera() + "\n";
        string += "Cursos:\n";
        for (Curso curso:cursos){
            string += "\tID: " + curso.getId() + "\n";
            string += "\tNombre: " + curso.getNombre() + "\n";
            string += "\tInstructor: " + curso.getInstructor() + "\n";
            string += "\tDuracion: " + curso.getDuracion() + "\n";
            string += "\tCosto: $" + curso.getCosto() + "\n";
        }
        string += "Total: $" + total;

        return string;
    }

}
