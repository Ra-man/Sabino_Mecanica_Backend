package com.sabinomecanica.backend.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class ServicoPeca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private Double precoCusto;
    private Double precoVenda;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public ServicoPeca() {}

    public ServicoPeca(UUID id, String nome, Double precoCusto, Double precoVenda, Servico servico) {
        this.id = id;
        this.nome = nome;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.servico = servico;
    }

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
