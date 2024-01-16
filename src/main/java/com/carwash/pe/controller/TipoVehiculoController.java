package com.carwash.pe.controller;

import com.carwash.pe.model.TipoVehiculo;
import com.carwash.pe.service.TipoVehiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("tipovehiculo")
public class TipoVehiculoController {

    private final TipoVehiculoService tipoVehiculoService;

    public TipoVehiculoController(TipoVehiculoService tipoVehiculoService) {
        this.tipoVehiculoService = tipoVehiculoService;
    }

    @GetMapping("/")
    public ResponseEntity<Flux<TipoVehiculo>> getTipoVehiculo() {
        return new ResponseEntity<>(tipoVehiculoService.findAllTipos(), HttpStatus.OK) {
        };
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<TipoVehiculo>> getTipoVehiculoById(String id) {
        return new ResponseEntity<>(tipoVehiculoService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> eliminarTipoVehiculo(@PathVariable("id") String id) {
        return new ResponseEntity<>(tipoVehiculoService.eliminarTipoVehiculo(id), HttpStatus.OK);
    }

}