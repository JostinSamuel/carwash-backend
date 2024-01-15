package com.carwash.pe.service;

import com.carwash.pe.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    private VehiculoRepository vehiculoRepository;

    public VehiculoService (VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository=vehiculoRepository;
    }


}
