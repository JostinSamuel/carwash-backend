package com.carwash.pe.service;

import com.carwash.pe.model.Vehiculo;
import com.carwash.pe.repository.TipoVehiculoRepository;
import com.carwash.pe.repository.VehiculoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    private VehiculoRepository vehiculoRepository;
    private TipoVehiculoRepository tipoVehiculoRepository;

    public VehiculoService (VehiculoRepository vehiculoRepository, TipoVehiculoRepository tipoVehiculoRepository) {
        this.vehiculoRepository=vehiculoRepository;
        this.tipoVehiculoRepository=tipoVehiculoRepository;
    }

    public Flux<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Mono<Vehiculo> obtenerVehiculo(String id) {
        return vehiculoRepository.findById(id);
    }
    
    public Mono<Vehiculo> insertarVehiculo(Vehiculo vehiculo) {
        return tipoVehiculoRepository.findById(vehiculo.getTipoVehiculo().getId())
        .switchIfEmpty(Mono.error(new RuntimeException("Tipo de vehículo no encontrado")))
        .flatMap(tipoVehiculo -> {
            vehiculo.setTipoVehiculo(tipoVehiculo);
            return vehiculoRepository.save(vehiculo);
        });
    }

    public Mono<Vehiculo> actualizarVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.findById(vehiculo.getId())
                .flatMap(vehiculoActualizado -> {
                    vehiculoActualizado.setId(vehiculo.getId());
                    vehiculoActualizado.setPlaca(vehiculo.getPlaca());
                    vehiculoActualizado.setMarca(vehiculo.getMarca());
                    vehiculoActualizado.setModelo(vehiculo.getModelo());
                    vehiculoActualizado.setAnio(vehiculo.getAnio());
                    vehiculoActualizado.setColor(vehiculo.getColor());
                    vehiculoActualizado.setInfoExtra(vehiculo.getInfoExtra());
                    vehiculoActualizado.setTipoVehiculo(vehiculo.getTipoVehiculo());
                    return vehiculoRepository.save(vehiculoActualizado);
                });
    }

    public Mono<Void> eliminarVehiculo(String id) {
        return vehiculoRepository.deleteById(id);
    }

}
