package com.carwash.pe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carwash.pe.model.Usuario;
import com.carwash.pe.service.UsuarioService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // Permite solicitudes desde el puerto 4200
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Flux<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Usuario>> obtenerUsuario(@PathVariable("id") String id) {
        Mono<Usuario> entity = usuarioService.obtenerUsuario(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mono<Usuario>> registrarUsuario(@RequestBody Usuario usuario) {
        Mono<Usuario> entity = usuarioService.insertarUsuario(usuario);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Mono<Usuario>> actualizarUsuario(@PathVariable("id") String id ,@RequestBody Usuario usuario) {
        //obtener id del usuario
        Mono<Usuario> usuarioActualizado = usuarioService.obtenerUsuario(id);
        //actualizar usuario
        usuarioActualizado = usuarioService.actualizarUsuario(usuario);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Mono<Void>> eliminarUsuario(@PathVariable("id") String id) {
        Mono<Void> entity = usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

}
