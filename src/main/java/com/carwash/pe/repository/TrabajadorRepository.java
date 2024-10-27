package com.carwash.pe.repository;

import com.carwash.pe.model.Trabajador;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends ReactiveCrudRepository<Trabajador, String> {
}
