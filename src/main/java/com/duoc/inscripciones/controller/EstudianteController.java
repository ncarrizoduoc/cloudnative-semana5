package com.duoc.inscripciones.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duoc.inscripciones.model.Estudiante;
import com.duoc.inscripciones.service.EstudianteService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes(){
        return ResponseEntity.ok(estudianteService.listarEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> buscarEstudiante(@PathVariable @PositiveOrZero Long id){
        return estudianteService.buscarEstudiante(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudiante> registrarEstudiante(@Valid @RequestBody Estudiante estudiante){
        estudiante.setId(null);
        Estudiante creado = estudianteService.registrarEstudiante(estudiante);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(creado.getId())
            .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> modificarEstudiante(@PathVariable @PositiveOrZero Long id, @Valid @RequestBody Estudiante estudiante){
        return estudianteService.modificarEstudiante(id, estudiante)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable @PositiveOrZero Long id){
        return estudianteService.eliminarEstudiante(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }

}
