package com.sabinomecanica.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Carro {

    @Id
    @GeneratedValue
    private UUID id;

    private String placa;
    private String modelo;
    private String marca;     // ✔ ADICIONADO
    private String cor;       // ✔ ADICIONADO
    private String fotoUrl;   // ✔ ADICIONADO

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "carro", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Servico> servicos;

    // GETTERS E SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }   // ✔
    public void setMarca(String marca) { this.marca = marca; } // ✔

    public String getCor() { return cor; }    // ✔
    public void setCor(String cor) { this.cor = cor; } // ✔

    public String getFotoUrl() { return fotoUrl; }  // ✔
    public void setFotoUrl(String fotoUrl) { this.fotoUrl = fotoUrl; } // ✔

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<Servico> getServicos() { return servicos; }
    public void setServicos(List<Servico> servicos) { this.servicos = servicos; }
}
