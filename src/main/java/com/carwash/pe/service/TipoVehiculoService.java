package com.carwash.pe.service;

import org.springframework.stereotype.Service;

import com.carwash.pe.model.TipoVehiculo;
import com.carwash.pe.repository.TipoVehiculoRepository;
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

    public Mono<TipoVehiculo> actualizarTipoVehiculo(TipoVehiculo tipoVehiculo) {
        return tipoVehiculoRepository.findById(tipoVehiculo.getId())
                .flatMap(tipoVehiculoActualizado -> {
                    tipoVehiculoActualizado.setId(tipoVehiculo.getId());
                    tipoVehiculoActualizado.setTipoVehiculo(tipoVehiculo.getTipoVehiculo());
                    return tipoVehiculoRepository.save(tipoVehiculoActualizado);
                });
    }

    public Mono<Void> eliminarTipoVehiculo(String id){
        return tipoVehiculoRepository.deleteById(id);
    }

}
