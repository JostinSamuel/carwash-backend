package com.carwash.pe.controller;

import com.carwash.pe.model.ClienteVehiculo;
import com.carwash.pe.service.ClienteVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clienteVehiculo")
public class ClienteVehiculoController {

    private final ClienteVehiculoService clienteVehiculoService;

    @GetMapping("/")
    public Flux<ClienteVehiculo> findAllClienteVehiculos() {
        return clienteVehiculoService.findAllClienteVehiculos();
    }

    @GetMapping("/{placa}")
    public Mono<ClienteVehiculo> findByPlaca(@PathVariable("placa") String placa){
        return clienteVehiculoService.findByPlaca(placa);
    }

    @PostMapping("/")
    public Mono<ClienteVehiculo> insertClienteVehiculo(@RequestBody ClienteVehiculo clienteVehiculo){
        return clienteVehiculoService.saveClienteVehiculo(clienteVehiculo);
    }

    @DeleteMapping("/{placa}")
    public Mono<Void> deleteClienteVehiculo(@PathVariable("placa") String placa){
        return clienteVehiculoService.deleteByPlacado(placa);
    }

    @PutMapping("/{placa}")
    public Mono<ClienteVehiculo> updateClienteVehiculo(@PathVariable("placa")  String placa, @RequestBody ClienteVehiculo clienteVehiculo){
        return clienteVehiculoService.updateClienteVehiculoMono(placa, clienteVehiculo);
    }
}
