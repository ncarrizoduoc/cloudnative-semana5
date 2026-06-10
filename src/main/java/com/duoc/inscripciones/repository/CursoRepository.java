package com.duoc.inscripciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.inscripciones.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{

}
