package com.carwash.pe.service;

import com.carwash.pe.model.TipoVehiculo;
import com.carwash.pe.repository.TipoVehiculoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TipoVehiculoService {

    private TipoVehiculoRepository tipoVehiculoRepository;

    public TipoVehiculoService(TipoVehiculoRepository tipoVehiculoRepository){
        this.tipoVehiculoRepository=tipoVehiculoRepository;
    }

    public Flux<TipoVehiculo> findAllTipos(){
        return tipoVehiculoRepository.findAll();
    };

    public Mono<TipoVehiculo> findById (String id ){
        return tipoVehiculoRepository.findById(id);
    }

    public Mono<TipoVehiculo> NewTipoVehiculo (TipoVehiculo tipoVehiculo){
        return tipoVehiculoRepository.save(tipoVehiculo);
    }

    public Mono<TipoVehiculo> updateTipoVehiculo (String id){
        return tipoVehiculoRepository.findById(id)
                .flatMap(tipo -> tipoVehiculoRepository.save(tipo));
    }

    public Mono<Void> eliminarTipoVehiculo(String id){
        return tipoVehiculoRepository.deleteById(id);
    }

}
