package com.duoc.inscripciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.inscripciones.repository.CursoRepository;
import com.duoc.inscripciones.model.Curso;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepo;

    public List<Curso> listarCursos(){
        return cursoRepo.findAll();
    }

    public Optional<Curso> buscarCurso(Long id){
        return cursoRepo.findById(id);
    }

    public Curso registrarCurso(Curso curso){
        System.out.println("Costo del curso: $" + curso.getCosto());
        return cursoRepo.save(curso);
    }

    public Optional<Curso> modificarCurso(Long id, Curso curso){
        if (cursoRepo.existsById(id)){
            curso.setId(id);
            return Optional.of(cursoRepo.save(curso));
        } else {
            return Optional.empty();
        }
    }

    public boolean eliminarCurso(Long id){
        if (cursoRepo.existsById(id)){
            cursoRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
