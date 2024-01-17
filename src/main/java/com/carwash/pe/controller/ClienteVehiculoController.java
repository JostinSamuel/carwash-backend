package com.carwash.pe.controller;

import com.carwash.pe.model.ClienteVehiculo;
import com.carwash.pe.repository.ClienteRepository;
import com.carwash.pe.repository.ClienteVehiculoRepository;
import com.carwash.pe.repository.VehiculoRepository;
import com.carwash.pe.service.ClienteService;
import com.carwash.pe.service.ClienteVehiculoService;
import com.carwash.pe.service.VehiculoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/clienteVehiculo")
public class ClienteVehiculoController {

    private ClienteVehiculoService clienteVehiculoService;
    private ClienteService clienteService;
    private VehiculoService vehiculoService;

    public ClienteVehiculoController(ClienteVehiculoService clienteVehiculoService,
                                     ClienteService clienteService,
                                     VehiculoService vehiculoService){
        this.clienteService = clienteService;
        this.clienteVehiculoService = clienteVehiculoService;
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("/")
    public Flux<ClienteVehiculo> findAllClienteVehiculos() {
        return clienteVehiculoService.findAllClienteVehiculos();
    }

    @GetMapping("/{placa}")
    public Mono<ClienteVehiculo> findByPlaca(@PathParam("placa") Mono<String> placa){
        return clienteVehiculoService.findByPlacado(placa);
    }

    @PostMapping("/")
    public Mono<ClienteVehiculo> insertClienteVehiculo(@RequestBody ClienteVehiculo clienteVehiculo){
        return clienteVehiculoService.saveCLienteVehiculo(clienteVehiculo);
    }

}
