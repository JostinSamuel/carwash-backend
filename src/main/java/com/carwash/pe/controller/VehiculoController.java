package com.carwash.pe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carwash.pe.model.Vehiculo;
import com.carwash.pe.service.VehiculoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {
    
    private final VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<Flux<Vehiculo>> listarVehiculos() {
        return new ResponseEntity<>(vehiculoService.listarVehiculos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Vehiculo>> obtenerVehiculo(@PathVariable("id") String id) {
        Mono<Vehiculo> entity = vehiculoService.obtenerVehiculo(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mono<Vehiculo>> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        Mono<Vehiculo> entity = vehiculoService.insertarVehiculo(vehiculo);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mono<Vehiculo>> actualizarVehiculo(@PathVariable("id") String id ,@RequestBody Vehiculo vehiculo) {
        //obtener id del vehiculo
        Mono<Vehiculo> vehiculoActualizado = vehiculoService.obtenerVehiculo(id);
        //actualizar vehiculo
        vehiculoActualizado = vehiculoService.actualizarVehiculo(vehiculo);
        return new ResponseEntity<>(vehiculoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mono<Void>> eliminarVehiculo(@PathVariable("id") String id) {
        Mono<Void> entity = vehiculoService.eliminarVehiculo(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    
}
