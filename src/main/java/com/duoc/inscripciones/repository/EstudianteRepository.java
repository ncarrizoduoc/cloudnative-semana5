package com.duoc.inscripciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.inscripciones.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{

}
