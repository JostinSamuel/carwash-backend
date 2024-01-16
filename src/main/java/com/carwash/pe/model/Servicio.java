package com.carwash.pe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "servicios")
public class Servicio {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String imagen;
}
