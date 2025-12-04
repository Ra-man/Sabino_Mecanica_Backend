package com.sabinomecanica.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ServicoPeca {

    @Id
    @GeneratedValue
    private UUID id;

    private String nome;
    private Double precoCusto;
    private Double precoVenda;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "servico_id")
    private Servico servico;

    // GETTERS & SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(Double precoCusto) { this.precoCusto = precoCusto; }

    public Double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Double precoVenda) { this.precoVenda = precoVenda; }

    public Servico getServico() { return servico; }
    public void setServico(Servico servico) { this.servico = servico; }
}
