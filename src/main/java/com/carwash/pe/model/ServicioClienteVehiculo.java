package com.carwash.pe.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "servicioClienteVehiculo")
public class ServicioClienteVehiculo {

    @Id
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    private EstadoPago estado;

    private String detalles;

    private Servicio servicio;

    private Cliente cliente;

    private Vehiculo vehiculo;
    
}
