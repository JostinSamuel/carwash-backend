package com.carwash.pe.repository;

import com.carwash.pe.model.TipoVehiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepository extends ReactiveCrudRepository<TipoVehiculo, String>{
    
}
