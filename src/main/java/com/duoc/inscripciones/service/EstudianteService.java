package com.duoc.inscripciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.inscripciones.repository.EstudianteRepository;
import com.duoc.inscripciones.model.Estudiante;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    
    @Autowired
    private EstudianteRepository estudianteRepo;

    public List<Estudiante> listarEstudiantes(){
        return estudianteRepo.findAll();
    }

    public Optional<Estudiante> buscarEstudiante(Long id){
        return estudianteRepo.findById(id);
    }

    public Estudiante registrarEstudiante(Estudiante estudiante){
        return estudianteRepo.save(estudiante);
    }

    public Optional<Estudiante> modificarEstudiante(Long id, Estudiante estudiante){
        if (estudianteRepo.existsById(id)){
            estudiante.setId(id);
            return Optional.of(estudianteRepo.save(estudiante));
        } else {
            return Optional.empty();
        }
    }

    public boolean eliminarEstudiante(Long id){
        if (estudianteRepo.existsById(id)){
            estudianteRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
