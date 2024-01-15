package com.carwash.pe.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehiculos")
public class Vehiculos {
    private String id;
    private String nombre;
    private String marca;
    private String modelo;
    private Integer año;
    private String color;
    private String infoExtra;
    private TipoVehiculo tipoVehiculo;
}
