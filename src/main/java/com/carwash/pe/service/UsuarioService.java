package com.carwash.pe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.carwash.pe.model.Usuario;
import com.carwash.pe.repository.UsuarioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    public Flux<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Mono<Usuario> obtenerUsuario(String id) {
        return usuarioRepository.findById(id);
    }
    
    public Mono<Usuario> insertarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Mono<Usuario> actualizarUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getId())
                .flatMap(usuarioActualizado -> {
                    usuarioActualizado.setId(usuario.getId());
                    usuarioActualizado.setUsername(usuario.getUsername());
                    usuarioActualizado.setPassword(usuario.getPassword());
                    usuarioActualizado.setRole(usuario.getRole());
                    return usuarioRepository.save(usuarioActualizado);
                });
    }

    public Mono<Void> eliminarUsuario(String id) {
        return usuarioRepository.deleteById(id);
    }
}
