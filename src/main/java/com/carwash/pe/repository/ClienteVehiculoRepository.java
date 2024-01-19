package com.carwash.pe.repository;

import com.carwash.pe.model.ClienteVehiculo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteVehiculoRepository extends ReactiveCrudRepository<ClienteVehiculo, String>{

    public Mono<ClienteVehiculo> findByPlaca(String placa);
}
