package com.sabinomecanica.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Parcela {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer numero;
    private Double valor;
    private Boolean pago;

    @ManyToOne
    @JsonIgnore       // 🔥 evita loop no JSON: Servico → Parcela → Servico
    @JoinColumn(name = "servico_id")
    private Servico servico;

    // GETTERS & SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Boolean getPago() { return pago; }
    public void setPago(Boolean pago) { this.pago = pago; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
}
