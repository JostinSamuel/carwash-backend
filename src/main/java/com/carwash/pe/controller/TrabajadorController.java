package com.carwash.pe.controller;

import com.carwash.pe.model.Trabajador;
import com.carwash.pe.service.TrabajadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController {

    private final TrabajadorService trabajadorService;

    @GetMapping
    public ResponseEntity<Flux<Trabajador>> listarTrabajador() {
        return new ResponseEntity<>(trabajadorService.listarTrabajador(), HttpStatus.OK);
    }

    @GetMapping("/{idTrabajador}")
    public ResponseEntity<Mono<Trabajador>> obtenerTrabajador(
            @PathVariable("idTrabajador") String idTrabajador) {
        Mono<Trabajador> entity = trabajadorService.obtenerTrabajador(idTrabajador);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Mono<Trabajador>> agregarTrabajador(@RequestBody Trabajador trabajador) {
        Mono<Trabajador> entity = trabajadorService.agregarTrabajador(trabajador);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{idTrabajador}")
    public ResponseEntity<Mono<Trabajador>> actualizarTrabajador(
            @PathVariable("idTrabajador") String idTrabajador, @RequestBody Trabajador trabajador) {
        Mono<Trabajador> trabajadorActualizado = trabajadorService.obtenerTrabajador(idTrabajador);
        trabajadorActualizado = trabajadorService.actualizarTrabajador(trabajador);
        return new ResponseEntity<>(trabajadorActualizado, HttpStatus.OK);
    }

    @DeleteMapping("eliminar/{idTrabajador}")
    public ResponseEntity<Mono<Void>> eliminarTrabajador(
            @PathVariable("idTrabajador") String idTrabajador) {
        Mono<Void> entity = trabajadorService.eliminarTrabajador(idTrabajador);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
