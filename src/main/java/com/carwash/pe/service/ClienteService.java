package com.carwash.pe.service;

import org.springframework.stereotype.Service;

import com.carwash.pe.model.Cliente;
import com.carwash.pe.model.Usuario;
import com.carwash.pe.repository.ClienteRepository;
import com.carwash.pe.repository.UsuarioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    
    private ClienteRepository clienteRepository;
    private UsuarioRepository usuarioRepository;
    
    public ClienteService(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }
    
    public Flux<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Mono<Cliente> obtenerCliente(String id) {
        return clienteRepository.findById(id);
    }
    
    public Mono<Cliente> insertarCliente(Cliente cliente) {
        Mono<Usuario> usuarioMono = cliente.getUsuario().getId() == null ? 
            usuarioRepository.save(cliente.getUsuario()) : 
            usuarioRepository.findById(cliente.getUsuario().getId());
    
        return usuarioMono.flatMap(usuario -> {
            cliente.setUsuario(usuario);
            return clienteRepository.save(cliente);
        });
    }

    public Mono<Cliente> actualizarCliente(Cliente cliente) {
        return clienteRepository.findById(cliente.getId())
                .flatMap(clienteActualizado -> {
                    clienteActualizado.setId(cliente.getId());
                    clienteActualizado.setNombre(cliente.getNombre());
                    clienteActualizado.setApellido(cliente.getApellido());
                    clienteActualizado.setDni(cliente.getDni());
                    clienteActualizado.setTelefono(cliente.getTelefono());
                    clienteActualizado.setDireccion(cliente.getDireccion());
                    clienteActualizado.setUsuario(cliente.getUsuario());
                    return clienteRepository.save(clienteActualizado);
                });
    }

    public Mono<Void> eliminarCliente(String id) {
        return clienteRepository.deleteById(id);
    }
}
