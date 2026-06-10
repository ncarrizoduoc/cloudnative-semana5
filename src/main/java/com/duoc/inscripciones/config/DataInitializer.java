package com.duoc.inscripciones.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.duoc.inscripciones.model.Curso;
import com.duoc.inscripciones.model.Estudiante;
import com.duoc.inscripciones.repository.CursoRepository;
import com.duoc.inscripciones.repository.EstudianteRepository;

@Component
public class DataInitializer implements ApplicationRunner{
    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private EstudianteRepository estudianteRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        //Crear cursos en la base de datos
        Curso curso1 = new Curso(null, "Introduccion a la programacion", "Profesor Pedro", 5, 250000);
        Curso curso2 = new Curso(null, "Etica", "Profesor Juan", 3, 120000);
        Curso curso3 = new Curso(null, "Biologia Celular", "Profesor Diego", 10, 475000);
        cursoRepo.save(curso1);
        cursoRepo.save(curso2);
        cursoRepo.save(curso3);

        Estudiante est1 = new Estudiante(null, "Jane Doe", "Ingenieria");
        Estudiante est2 = new Estudiante(null, "Juan Perez", "Medicina");
        Estudiante est3 = new Estudiante(null, "Maria Jimenez", "Derecho");
        estudianteRepo.save(est1);
        estudianteRepo.save(est2);
        estudianteRepo.save(est3);
        
    }


}
