package com.carwash.pe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carwash.pe.model.Cliente;
import com.carwash.pe.service.ClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Flux<Cliente>> listarClientes() {
        return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Cliente>> obtenerCliente(@PathVariable("id") String id) {
        Mono<Cliente> entity = clienteService.obtenerCliente(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mono<Cliente>> registrarCliente(@RequestBody Cliente cliente) {
        Mono<Cliente> entity = clienteService.insertarCliente(cliente);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mono<Cliente>> actualizarCliente(@PathVariable("id") String id ,@RequestBody Cliente cliente) {
        //obtener id del cliente
        Mono<Cliente> clienteActualizado = clienteService.obtenerCliente(id);
        //actualizar cliente
        clienteActualizado = clienteService.actualizarCliente(cliente);
        return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mono<Void>> eliminarCliente(@PathVariable("id") String id) {
        Mono<Void> entity = clienteService.eliminarCliente(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }
}
