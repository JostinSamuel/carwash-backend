package com.carwash.pe.service;

import com.carwash.pe.model.Cliente;
import com.carwash.pe.model.ClienteVehiculo;
import com.carwash.pe.model.Usuario;
import com.carwash.pe.model.Vehiculo;
import com.carwash.pe.repository.ClienteRepository;
import com.carwash.pe.repository.ClienteVehiculoRepository;
import com.carwash.pe.repository.VehiculoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteVehiculoService {

    private ClienteVehiculoRepository clienteVehiculoRepository;
    private ClienteRepository clienteRepository;
    private VehiculoRepository vehiculoRepository;
    public ClienteVehiculoService(ClienteVehiculoRepository clienteVehiculoRepository,
                                  ClienteRepository clienteRepository,
                                    VehiculoRepository vehiculoRepository){
        this.clienteVehiculoRepository = clienteVehiculoRepository;
        this.clienteRepository =  clienteRepository;
        this.vehiculoRepository =  vehiculoRepository;
    }

    public Flux<ClienteVehiculo> findAllClienteVehiculos(){
        return clienteVehiculoRepository.findAll();
    }

    public Mono<ClienteVehiculo> findById(Mono<String> id){
        return clienteVehiculoRepository.findById(id);
    }

    public Mono<ClienteVehiculo> findByPlacado(Mono<String> placa){
        return clienteVehiculoRepository.findByPlaca(placa);
    }

    public Mono<ClienteVehiculo> saveCLienteVehiculo(ClienteVehiculo clienteVe){

        Mono<Cliente> monoCliente = clienteRepository.findById(clienteVe.getCliente().getId());

        Mono<Vehiculo> monoVehiculo = vehiculoRepository.findById(clienteVe.getVehiculo().getId());

        return Mono.zip(monoCliente, monoVehiculo)
                .flatMap(multipleCast -> {
                    Cliente cli1 = multipleCast.getT1();
                    Vehiculo cli2 = multipleCast.getT2();

                    clienteVe.setCliente(cli1);
                    clienteVe.setVehiculo(cli2);
                    return clienteVehiculoRepository.save(clienteVe);
                });
    }



}
