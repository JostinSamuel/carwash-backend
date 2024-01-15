package com.carwash.pe.repository;

import com.carwash.pe.model.Vehiculos;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends ReactiveCrudRepository<Vehiculos, String>{
    
}
