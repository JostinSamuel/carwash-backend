package com.carwash.pe.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.carwash.pe.model.ServicioClienteVehiculo;

@Repository
public interface ServicioClienteVehiculoRepository extends ReactiveCrudRepository<ServicioClienteVehiculo, String>{
    
}
