package com.carwash.pe.service;

import org.springframework.stereotype.Service;

import com.carwash.pe.model.Servicio;
import com.carwash.pe.repository.ServicioRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioService {
    
    private ServicioRepository servicioRepository;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public Flux<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    public Mono<Servicio> obtenerServicio(String id) {
        return servicioRepository.findById(id);
    }
    
    public Mono<Servicio> insertarServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Mono<Servicio> actualizarServicio(Servicio servicio) {
        return servicioRepository.findById(servicio.getId())
                .flatMap(servicioActualizado -> {
                    servicioActualizado.setId(servicio.getId());
                    servicioActualizado.setNombre(servicio.getNombre());
                    servicioActualizado.setDescripcion(servicio.getDescripcion());
                    servicioActualizado.setPrecio(servicio.getPrecio());
                    servicioActualizado.setImagen(servicio.getImagen());
                    return servicioRepository.save(servicioActualizado);
                });
    }

    public Mono<Void> eliminarServicio(String id) {
        return servicioRepository.deleteById(id);
    }
}
