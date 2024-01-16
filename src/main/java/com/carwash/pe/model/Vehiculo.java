package com.carwash.pe.model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehiculos")
public class Vehiculo {
    @Id
    private String id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private String color;
    private String infoExtra;
    private TipoVehiculo tipoVehiculo;
}
