package com.duoc.inscripciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.inscripciones.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

}
