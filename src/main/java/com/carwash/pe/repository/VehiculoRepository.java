package com.carwash.pe.repository;

import com.carwash.pe.model.Vehiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends ReactiveCrudRepository<Vehiculo, String>{
    
}
