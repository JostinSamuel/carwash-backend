package com.carwash.pe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carwash.pe.model.ServicioClienteVehiculo;
import com.carwash.pe.service.ServicioClienteVehiculoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/servicioClienteVehiculo")
public class ServicioClienteVehiculoController {
    
    ServicioClienteVehiculoService servicioClienteVehiculoService;

    public ServicioClienteVehiculoController(ServicioClienteVehiculoService servicioClienteVehiculoService) {
        this.servicioClienteVehiculoService = servicioClienteVehiculoService;
    }

    @GetMapping
    public ResponseEntity<Flux<ServicioClienteVehiculo>> findAllServicioClienteVehiculo() {
        return new ResponseEntity<>(servicioClienteVehiculoService.findAllServicioClienteVehiculo(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<ServicioClienteVehiculo>> findById(@PathVariable("id") String id){
        Mono<ServicioClienteVehiculo> scv = servicioClienteVehiculoService.findById(id);
        return new ResponseEntity<>(scv, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Mono<ServicioClienteVehiculo>> insertServicioClienteVehiculo(@RequestBody ServicioClienteVehiculo servicioClienteVehiculo){
        return new ResponseEntity<>(servicioClienteVehiculoService.saveServicioClienteVehiculo(servicioClienteVehiculo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> deleteServicioClienteVehiculo(@PathVariable("id") String id){
        Mono<Void> scv = servicioClienteVehiculoService.deleteById(id);
        return new ResponseEntity<>(scv, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<ServicioClienteVehiculo>> updateClienteVehiculo(@PathVariable("id")  String id, @RequestBody ServicioClienteVehiculo servicioClienteVehiculo){
        Mono<ServicioClienteVehiculo> scvActualizado = servicioClienteVehiculoService.findById(id);
        scvActualizado = servicioClienteVehiculoService.updateServicioClienteVehiculo(servicioClienteVehiculo);
        return new ResponseEntity<>(scvActualizado, HttpStatus.OK);
    }

}
