package com.carwash.pe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carwash.pe.model.Servicio;
import com.carwash.pe.service.ServicioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<Flux<Servicio>> listarServicios() {
        return new ResponseEntity<>(servicioService.listarServicios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Servicio>> obtenerServicio(@PathVariable("id") String id) {
        Mono<Servicio> entity = servicioService.obtenerServicio(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mono<Servicio>> registrarServicio(@RequestBody Servicio servicio) {
        Mono<Servicio> entity = servicioService.insertarServicio(servicio);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mono<Servicio>> actualizarServicio(@PathVariable("id") String id ,@RequestBody Servicio servicio) {
        //obtener id del servicio
        Mono<Servicio> servicioActualizado = servicioService.obtenerServicio(id);
        //actualizar servicio
        servicioActualizado = servicioService.actualizarServicio(servicio);
        return new ResponseEntity<>(servicioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mono<Void>> eliminarServicio(@PathVariable("id") String id) {
        Mono<Void> entity = servicioService.eliminarServicio(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    
}
