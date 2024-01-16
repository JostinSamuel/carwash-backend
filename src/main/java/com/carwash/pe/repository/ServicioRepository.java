package com.carwash.pe.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.carwash.pe.model.Servicio;

@Repository
public interface ServicioRepository extends ReactiveCrudRepository<Servicio, String>{
    
}
