// src/main/java/com/sabinomecanica/backend/models/Carro.java
package com.sabinomecanica.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "carros")
public class Carro {

    @Id
    @GeneratedValue
    private UUID id;

    private String placa;

    private String modelo;

    private String marca;

    private String cor;

    private String fotoUrl;

    // IMPEDIR LOOP Cliente → Carros → Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"carros"})
    private Cliente cliente;

    public Carro() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
