package com.carwash.pe.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientes")
public class Cliente {

    private String id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String direccion;
}
