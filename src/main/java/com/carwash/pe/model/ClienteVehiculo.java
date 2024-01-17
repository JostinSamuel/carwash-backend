package com.carwash.pe.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clienteVehiculo")
public class ClienteVehiculo {

    @Id
    private String placa;

    private Vehiculo vehiculo;

    private Cliente cliente;

}
