package com.sabinomecanica.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "servico_peca")
@NoArgsConstructor
@AllArgsConstructor
public class ServicoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String descricao;

    private double precoPago;

    private double precoCobrado;

    @ManyToOne
    @JoinColumn(name = "id_servico", nullable = false)
    @JsonIgnore // evita loop infinito no JSON (servico -> itens -> servico...)
    private Servico servico;

    // GETTERS / SETTERS

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(double precoPago) {
        this.precoPago = precoPago;
    }

    public double getPrecoCobrado() {
        return precoCobrado;
    }

    public void setPrecoCobrado(double precoCobrado) {
        this.precoCobrado = precoCobrado;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}