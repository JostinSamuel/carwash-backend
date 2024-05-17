package com.carwash.pe.service;

import com.carwash.pe.model.Cliente;
import com.carwash.pe.model.ClienteVehiculo;
import com.carwash.pe.model.Vehiculo;
import com.carwash.pe.repository.ClienteRepository;
import com.carwash.pe.repository.ClienteVehiculoRepository;
import com.carwash.pe.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class ClienteVehiculoService {

    private final ClienteVehiculoRepository clienteVehiculoRepository;
    private final ClienteRepository clienteRepository;
    private final VehiculoRepository vehiculoRepository;

    public Flux<ClienteVehiculo> findAllClienteVehiculos(){
        return clienteVehiculoRepository.findAll();
    }

    public Mono<ClienteVehiculo> findById(Mono<String> id){
        return clienteVehiculoRepository.findById(id);
    }

    public Mono<ClienteVehiculo> findByPlaca(String placa){
        return clienteVehiculoRepository.findByPlaca(placa);
    }

    public Mono<ClienteVehiculo> saveClienteVehiculo(ClienteVehiculo clienteVe){

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

    public Mono<Void> deleteByPlacado(String placa){
        return clienteVehiculoRepository.deleteById(placa);
    };
    @Transactional
    public Mono<ClienteVehiculo> updateClienteVehiculoMono(String placa, ClienteVehiculo clienteVehiculo){
        return clienteVehiculoRepository.findByPlaca(placa)
                .flatMap(vehiculo -> {
                    vehiculo.setCliente((clienteVehiculo.getCliente()));
                    vehiculo.setVehiculo(clienteVehiculo.getVehiculo());
                    vehiculo.setPlaca(clienteVehiculo.getPlaca());
                    vehiculo.setDetalles(clienteVehiculo.getDetalles());
                    return clienteVehiculoRepository.save(vehiculo);
                });
    };

    /*
    @Transactional
    public Mono<ClienteVehiculo> updateClienteVehiculo(String placa, ClienteVehiculo clienteVehiculo) {
        Mono<Cliente> clienteList = clienteRepository.findById(clienteVehiculo.getCliente().getId());
        Mono<Vehiculo> VehiculosList = vehiculoRepository.findById(clienteVehiculo.getCliente().getId());
        Mono<ClienteVehiculo> mono = clienteVehiculoRepository.findByPlaca(placa); // <T>
        return Mono.zip(clienteList, VehiculosList, mono)
                .flatMap(multipleCast -> {

                        Cliente cli1 = multipleCast.getT1();
                        Vehiculo cli2 = multipleCast.getT2();
                        clienteVehiculo.setCliente(cli1);
                        clienteVehiculo.setVehiculo(cli2);

                        return clienteVehiculoRepository.save(clienteVehiculo);

                });
    }
    */




}
