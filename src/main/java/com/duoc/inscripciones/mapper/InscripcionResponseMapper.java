package com.duoc.inscripciones.mapper;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.duoc.inscripciones.dto.InscripcionResponse;
import com.duoc.inscripciones.exception.ResourceNotFoundException;
import com.duoc.inscripciones.model.Curso;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.repository.CursoRepository;

@Component
public class InscripcionResponseMapper {

    @Autowired
    private CursoRepository cursoRepo;

    public InscripcionResponse toInscripcionResponse(Inscripcion inscripcion){
        InscripcionResponse response = new InscripcionResponse();
        response.setCursos(new ArrayList<Curso>());
        int total = 0;
        response.setId(inscripcion.getId());
        response.setEstudiante(inscripcion.getEstudiante());
        
        //Se itera sobre la lista de cursos
        for (Long cursoId : inscripcion.getCursos()){
            if (cursoRepo.existsById(cursoId)){
                Curso curso = cursoRepo.findById(cursoId).get();
                total += curso.getCosto();
                response.getCursos().add(curso);
            } else {
                throw new ResourceNotFoundException("No existe el curso con ID: " + cursoId);
            }
        }
        response.setTotal(total);

        return response;
    }

}
