package com.carwash.pe.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.carwash.pe.model.Usuario;

@Repository
public interface UsuarioRepository extends ReactiveCrudRepository<Usuario, String>{
    
}
