package com.sabinomecanica.backend.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Servico {

    @Id
    @GeneratedValue
    private UUID id;

    private String descricao;
    private String dataInicio;
    private String dataFim;

    private Double valorMaoObra;
    private Double valorGasto;
    private Double valorTotal;

    private Boolean temGarantia;
    private Integer tempoGarantiaDias;

    private String formaPagamento;
    private Integer numeroParcelas;
    private Double jurosPercentual;
    private String chequeData;

    private String tempoMaoObra;

    private String status; // EM_ANDAMENTO / CONCLUIDO

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "carro_id")
    private Carro carro;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoPeca> pecas;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcela> parcelas;

    // GETTERS & SETTERS
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public Double getValorMaoObra() { return valorMaoObra; }
    public void setValorMaoObra(Double valorMaoObra) { this.valorMaoObra = valorMaoObra; }

    public Double getValorGasto() { return valorGasto; }
    public void setValorGasto(Double valorGasto) { this.valorGasto = valorGasto; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public Boolean getTemGarantia() { return temGarantia; }
    public void setTemGarantia(Boolean temGarantia) { this.temGarantia = temGarantia; }

    public Integer getTempoGarantiaDias() { return tempoGarantiaDias; }
    public void setTempoGarantiaDias(Integer tempoGarantiaDias) { this.tempoGarantiaDias = tempoGarantiaDias; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public Integer getNumeroParcelas() { return numeroParcelas; }
    public void setNumeroParcelas(Integer numeroParcelas) { this.numeroParcelas = numeroParcelas; }

    public Double getJurosPercentual() { return jurosPercentual; }
    public void setJurosPercentual(Double jurosPercentual) { this.jurosPercentual = jurosPercentual; }

    public String getChequeData() { return chequeData; }
    public void setChequeData(String chequeData) { this.chequeData = chequeData; }

    public String getTempoMaoObra() { return tempoMaoObra; }
    public void setTempoMaoObra(String tempoMaoObra) { this.tempoMaoObra = tempoMaoObra; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Carro getCarro() { return carro; }
    public void setCarro(Carro carro) { this.carro = carro; }

    public List<ServicoPeca> getPecas() { return pecas; }
    public void setPecas(List<ServicoPeca> pecas) { this.pecas = pecas; }

    public List<Parcela> getParcelas() { return parcelas; }
    public void setParcelas(List<Parcela> parcelas) { this.parcelas = parcelas; }
}
