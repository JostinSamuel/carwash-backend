package com.carwash.pe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carwash.pe.model.TipoVehiculo;
import com.carwash.pe.service.TipoVehiculoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipoVehiculo")
@RequiredArgsConstructor
public class TipoVehiculoController {

    private final TipoVehiculoService tipoVehiculoService;

    @GetMapping
    public ResponseEntity<Flux<TipoVehiculo>> listarTipoVehiculos() {
        return new ResponseEntity<>(tipoVehiculoService.findAllTipos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<TipoVehiculo>> obtenerTipoVehiculo(@PathVariable("id") String id) {
        Mono<TipoVehiculo> entity = tipoVehiculoService.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mono<TipoVehiculo>> registrarTipoVehiculo(@RequestBody TipoVehiculo tipoVehiculo) {
        Mono<TipoVehiculo> entity = tipoVehiculoService.newTipoVehiculo(tipoVehiculo);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mono<TipoVehiculo>> actualizarTipoVehiculo(@PathVariable("id") String id ,@RequestBody TipoVehiculo tipoVehiculo) {
        //obtener id del tipoVehiculo
        Mono<TipoVehiculo> tipoVehiculoActualizado = tipoVehiculoService.findById(id);
        //actualizar tipoVehiculo
        tipoVehiculoActualizado = tipoVehiculoService.actualizarTipoVehiculo(tipoVehiculo);
        return new ResponseEntity<>(tipoVehiculoActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mono<Void>> eliminarTipoVehiculo(@PathVariable("id") String id) {
        Mono<Void> entity = tipoVehiculoService.eliminarTipoVehiculo(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
    
}
