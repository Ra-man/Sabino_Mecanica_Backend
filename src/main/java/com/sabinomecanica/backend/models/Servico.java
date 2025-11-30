package com.sabinomecanica.backend.models;

import com.sabinomecanica.backend.models.enums.FormaPagamento;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    private String dataInicio;
    private String dataFim;

    private Boolean temGarantia;
    private Integer tempoGarantiaDias;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Double valorMaoObra;
    private String tempoMaoObra;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoPeca> pecas = new ArrayList<>();

    private Double valorGasto;
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    private Integer numeroParcelas;
    private Double jurosPercentual;
    private String chequeData;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas = new ArrayList<>();

    private String status;

    public Servico() {}

    public Servico(UUID id, Cliente cliente, Carro carro, String dataInicio, String dataFim,
                   Boolean temGarantia, Integer tempoGarantiaDias, String descricao,
                   Double valorMaoObra, String tempoMaoObra, List<ServicoPeca> pecas, Double valorGasto,
                   Double valorTotal, FormaPagamento formaPagamento, Integer numeroParcelas,
                   Double jurosPercentual, String chequeData, List<Parcela> parcelas, String status) {

        this.id = id;
        this.cliente = cliente;
        this.carro = carro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.temGarantia = temGarantia;
        this.tempoGarantiaDias = tempoGarantiaDias;
        this.descricao = descricao;
        this.valorMaoObra = valorMaoObra;
        this.tempoMaoObra = tempoMaoObra;
        this.pecas = pecas;
        this.valorGasto = valorGasto;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.numeroParcelas = numeroParcelas;
        this.jurosPercentual = jurosPercentual;
        this.chequeData = chequeData;
        this.parcelas = parcelas;
        this.status = status;
    }

    // ========= GETTERS E SETTERS =========

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Carro getCarro() { return carro; }
    public void setCarro(Carro carro) { this.carro = carro; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public Boolean getTemGarantia() { return temGarantia; }
    public void setTemGarantia(Boolean temGarantia) { this.temGarantia = temGarantia; }

    public Integer getTempoGarantiaDias() { return tempoGarantiaDias; }
    public void setTempoGarantiaDias(Integer tempoGarantiaDias) { this.tempoGarantiaDias = tempoGarantiaDias; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValorMaoObra() { return valorMaoObra; }
    public void setValorMaoObra(Double valorMaoObra) { this.valorMaoObra = valorMaoObra; }

    public String getTempoMaoObra() { return tempoMaoObra; }
    public void setTempoMaoObra(String tempoMaoObra) { this.tempoMaoObra = tempoMaoObra; }

    public List<ServicoPeca> getPecas() { return pecas; }
    public void setPecas(List<ServicoPeca> pecas) { this.pecas = pecas; }

    public Double getValorGasto() { return valorGasto; }
    public void setValorGasto(Double valorGasto) { this.valorGasto = valorGasto; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(FormaPagamento formaPagamento) { this.formaPagamento = formaPagamento; }

    public Integer getNumeroParcelas() { return numeroParcelas; }
    public void setNumeroParcelas(Integer numeroParcelas) { this.numeroParcelas = numeroParcelas; }

    public Double getJurosPercentual() { return jurosPercentual; }
    public void setJurosPercentual(Double jurosPercentual) { this.jurosPercentual = jurosPercentual; }

    public String getChequeData() { return chequeData; }
    public void setChequeData(String chequeData) { this.chequeData = chequeData; }

    public List<Parcela> getParcelas() { return parcelas; }
    public void setParcelas(List<Parcela> parcelas) { this.parcelas = parcelas; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
