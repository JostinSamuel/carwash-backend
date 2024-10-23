package com.carwash.pe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.carwash.pe.model.Cliente;
import com.carwash.pe.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
  
    public Flux<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Mono<Cliente> obtenerCliente(String id) {
        return clienteRepository.findById(id);
    }
    
    public Mono<Cliente> insertarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
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
                    return clienteRepository.save(clienteActualizado);
                });
    }

    public Mono<Void> eliminarCliente(String id) {
        return clienteRepository.deleteById(id);
    }
}
