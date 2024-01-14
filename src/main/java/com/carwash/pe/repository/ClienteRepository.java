package com.carwash.pe.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.carwash.pe.model.Cliente;

@Repository
public interface ClienteRepository extends ReactiveCrudRepository<Cliente, String>{
    
}
