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

import com.carwash.pe.model.Cliente;
import com.carwash.pe.service.ClienteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

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
