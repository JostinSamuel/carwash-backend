package com.carwash.pe.service;

import com.carwash.pe.model.Trabajador;
import com.carwash.pe.repository.TrabajadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;

    public Flux<Trabajador> listarTrabajador() {
        return trabajadorRepository.findAll();
    }

    public Mono<Trabajador> obtenerTrabajador(String idTrabajador) {
        return trabajadorRepository.findById(idTrabajador);
    }

    public Mono<Trabajador> agregarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public Mono<Trabajador> actualizarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.findById(trabajador.getIdTrabajador())
                .flatMap(trabajadorActualizado -> {
                   trabajadorActualizado.setIdTrabajador(trabajador.getIdTrabajador());
                   trabajadorActualizado.setNombre(trabajador.getNombre());
                   trabajadorActualizado.setDni(trabajador.getDni());
                   trabajadorActualizado.setTelefono(trabajador.getTelefono());
                   return trabajadorRepository.save(trabajadorActualizado);
                });
    }

    public Mono<Void> eliminarTrabajador(String idTrabajador) {
        return trabajadorRepository.deleteById(idTrabajador);
    }
}
