package com.duoc.inscripciones.mapper;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.exception.ResourceNotFoundException;
import com.duoc.inscripciones.model.Estudiante;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.repository.CursoRepository;
import com.duoc.inscripciones.repository.EstudianteRepository;

@Component
public class InscripcionRequestMapper {

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Autowired
    private CursoRepository cursoRepo;

    public Inscripcion toInscripcion(InscripcionRequest request){
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCursos(new HashSet<Long>());
        
        //Guardar estudiante en inscripcion
        Long estudianteId = Long.valueOf(request.getEstudiante());
        if (estudianteRepo.existsById(estudianteId)){
            Estudiante estudiante = estudianteRepo.findById(estudianteId).get();
            inscripcion.setEstudiante(estudiante);
        } else {
            throw new ResourceNotFoundException("No existe el estudiante con el ID ingresado");
        }

        //Guardar cursos
        for(Long cursoId : request.getCursos()){
            if (cursoRepo.existsById(cursoId)){
                inscripcion.getCursos().add(cursoId);
            } else {
                throw new ResourceNotFoundException("No existe el curso con el ID: " + cursoId);
            }
        }

        return inscripcion;

    }

}
