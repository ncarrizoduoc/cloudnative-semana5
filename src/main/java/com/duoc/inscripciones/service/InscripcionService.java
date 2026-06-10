package com.duoc.inscripciones.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.inscripciones.repository.InscripcionRepository;
import com.duoc.inscripciones.dto.InscripcionResponse;
import com.duoc.inscripciones.mapper.InscripcionResponseMapper;
import com.duoc.inscripciones.model.Inscripcion;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepo;

    @Autowired
    private InscripcionResponseMapper mapperResponse;

    public List<Inscripcion> listarInscripciones(){
        return inscripcionRepo.findAll();
    }

    public Optional<InscripcionResponse> buscarInscripcion(Long id){
        if (inscripcionRepo.existsById(id)){
            return Optional.of(mapperResponse.toInscripcionResponse(inscripcionRepo.findById(id).get()));
        } else {
            return Optional.empty();
        }
    }

    public InscripcionResponse registrarInscripcion(Inscripcion inscripcion){
        inscripcion.setId(null);
        return mapperResponse.toInscripcionResponse(inscripcionRepo.save(inscripcion));
    }

    public boolean eliminarInscripcion(Long id){
        if (inscripcionRepo.existsById(id)){
            inscripcionRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
