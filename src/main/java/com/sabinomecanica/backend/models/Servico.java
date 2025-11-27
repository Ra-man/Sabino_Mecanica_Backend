package com.sabinomecanica.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "servico")
@NoArgsConstructor
@AllArgsConstructor
public class Servico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Date data_ini;

    private Date data_fim;

    private Date data_garantia;

    private String descricao;

    private double preco_mao_obra;

    private double valor_total;

    private String Status;

    @ManyToOne
    @JoinColumn(name = "id_carro")
    private Carro carro;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoPeca> itens = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getData_ini() {
        return data_ini;
    }

    public void setData_ini(Date data_ini) {
        this.data_ini = data_ini;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    public Date getData_garantia() {
        return data_garantia;
    }

    public void setData_garantia(Date data_garantia) {
        this.data_garantia = data_garantia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public double getValor_total() {
        return valor_total;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPreco_mao_obra() {
        return preco_mao_obra;
    }

    public void setPreco_mao_obra(double preco_mao_obra) {
        this.preco_mao_obra = preco_mao_obra;
    }

    public List<ServicoPeca> getItens() {
        return itens;
    }

    public void setItens(List<ServicoPeca> itens) {
        this.itens = itens;
    }
}
