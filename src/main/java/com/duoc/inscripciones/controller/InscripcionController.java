package com.duoc.inscripciones.controller;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.dto.InscripcionResponse;
import com.duoc.inscripciones.mapper.InscripcionRequestMapper;
import com.duoc.inscripciones.mapper.InscripcionResponseMapper;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.service.InscripcionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private InscripcionRequestMapper mapperRequest;

    @Autowired
    private InscripcionResponseMapper mapperResponse;

    @GetMapping
    public ResponseEntity<List<InscripcionResponse>> listarInscripciones(){
        List<InscripcionResponse> inscripciones = inscripcionService.listarInscripciones()
            .stream()
            .map(mapperResponse::toInscripcionResponse)
            .toList();
        return ResponseEntity.ok(inscripciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InscripcionResponse> buscarInscripcion(@PathVariable @PositiveOrZero Long id){
        return inscripcionService.buscarInscripcion(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InscripcionResponse> registrarInscripcion(@Valid @RequestBody InscripcionRequest request){
        Inscripcion inscripcion = mapperRequest.toInscripcion(request);
        InscripcionResponse creado = inscripcionService.registrarInscripcion(inscripcion);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(creado.getId())
            .toUri();
        return ResponseEntity.created(location).body(creado);
    }

    @PostMapping("/download")
    public ResponseEntity<ByteArrayResource> registrarYDescargarInscripcion(@Valid @RequestBody InscripcionRequest request){
        Inscripcion inscripcion = mapperRequest.toInscripcion(request);
        InscripcionResponse creado = inscripcionService.registrarInscripcion(inscripcion);

        //Convertir a ByteArrayResource
        //byte[] data = SerializationUtils.serialize(creado);
        byte[] data = creado.toString().getBytes(StandardCharsets.UTF_8);
        ByteArrayResource resource = new ByteArrayResource(data);

        //Generar respuesta con archivo descargable
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(creado.getId())
            .toUri();
        return ResponseEntity
            .created(location)
            .contentLength(data.length)
            .contentType(MediaType.TEXT_PLAIN)
            .header("Content-Disposition", "attachment; filename\"" + creado.getId() + "\"")
            .body(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable @PositiveOrZero Long id){
        return inscripcionService.eliminarInscripcion(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }


}
