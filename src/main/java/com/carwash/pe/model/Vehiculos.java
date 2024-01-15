package com.carwash.pe.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "vehiculo")
public class Vehiculos {
    private Long id;
    private String nombre;
    private String marca;
    private String modelo;
    private int año;
    private String color;
    private String infoExtra;
    private TipoVehiculo tipoVehiculo;

}
