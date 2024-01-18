package com.carwash.pe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carwash.pe.model.Cliente;
import com.carwash.pe.model.Servicio;
import com.carwash.pe.model.ServicioClienteVehiculo;
import com.carwash.pe.model.Vehiculo;
import com.carwash.pe.repository.ClienteRepository;
import com.carwash.pe.repository.ServicioClienteVehiculoRepository;
import com.carwash.pe.repository.ServicioRepository;
import com.carwash.pe.repository.VehiculoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServicioClienteVehiculoService {

    private ServicioClienteVehiculoRepository servicioClienteVehiculoRepository;
    private ServicioRepository servicioRepository;
    private ClienteRepository clienteRepository;
    private VehiculoRepository vehiculoRepository;

    public ServicioClienteVehiculoService(ServicioClienteVehiculoRepository servicioClienteVehiculoRepository,
            ServicioRepository servicioRepository, ClienteRepository clienteRepository,
            VehiculoRepository vehiculoRepository) {
        this.servicioClienteVehiculoRepository = servicioClienteVehiculoRepository;
        this.servicioRepository = servicioRepository;
        this.clienteRepository = clienteRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public Flux<ServicioClienteVehiculo> findAllServicioClienteVehiculo() {
        return servicioClienteVehiculoRepository.findAll();
    }

    public Mono<ServicioClienteVehiculo> findById(String id) {
        return servicioClienteVehiculoRepository.findById(id);
    }

    public Mono<ServicioClienteVehiculo> saveServicioClienteVehiculo(ServicioClienteVehiculo servicioClienteVehiculo) {

        Mono<Servicio> monoServicio = servicioRepository.findById(servicioClienteVehiculo.getServicio().getId());

        Mono<Cliente> monoCliente = clienteRepository.findById(servicioClienteVehiculo.getCliente().getId());

        Mono<Vehiculo> monoVehiculo = vehiculoRepository.findById(servicioClienteVehiculo.getVehiculo().getId());

        return Mono.zip(monoServicio, monoCliente, monoVehiculo)
                .flatMap(multipleCast -> {
                    Servicio servicio = multipleCast.getT1();
                    Cliente cliente = multipleCast.getT2();
                    Vehiculo vehiculo = multipleCast.getT3();

                    servicioClienteVehiculo.setServicio(servicio);
                    servicioClienteVehiculo.setCliente(cliente);
                    servicioClienteVehiculo.setVehiculo(vehiculo);
                    return servicioClienteVehiculoRepository.save(servicioClienteVehiculo);
                });
    }

    @Transactional
    public Mono<ServicioClienteVehiculo> updateServicioClienteVehiculo(
            ServicioClienteVehiculo servicioClienteVehiculo) {
        return servicioClienteVehiculoRepository.findById(servicioClienteVehiculo.getId())
                .flatMap(scv -> {
                    scv.setId(servicioClienteVehiculo.getId());
                    scv.setFecha(servicioClienteVehiculo.getFecha());
                    scv.setEstado(servicioClienteVehiculo.getEstado());
                    scv.setDetalles(servicioClienteVehiculo.getDetalles());
                    scv.setServicio(servicioClienteVehiculo.getServicio());
                    scv.setCliente(servicioClienteVehiculo.getCliente());
                    scv.setVehiculo(servicioClienteVehiculo.getVehiculo());
                    return servicioClienteVehiculoRepository.save(scv);
                });
    };

    public Mono<Void> deleteById(String id) {
        return servicioClienteVehiculoRepository.deleteById(id);
    }

}
