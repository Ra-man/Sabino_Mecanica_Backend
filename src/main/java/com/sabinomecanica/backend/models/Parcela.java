package com.sabinomecanica.backend.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Integer numero;
    private Double valor;
    private Boolean pago = false;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Parcela() {}

    public Parcela(UUID id, Integer numero, Double valor, Boolean pago, Servico servico) {
        this.id = id;
        this.numero = numero;
        this.valor = valor;
        this.pago = pago;
        this.servico = servico;
    }

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
