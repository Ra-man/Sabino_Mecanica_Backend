package com.sabinomecanica.backend.services;

import com.sabinomecanica.backend.models.Parcela;
import com.sabinomecanica.backend.models.Servico;
import com.sabinomecanica.backend.models.ServicoPeca;
import com.sabinomecanica.backend.models.enums.FormaPagamento;
import com.sabinomecanica.backend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(UUID id) {
        return servicoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Servico salvar(Servico servico) {

        // ========= VINCULA PEÇAS E CALCULA TOTAL GASTO / COBRADO =========
        double totalGasto = 0.0;
        double totalCobradoPecas = 0.0;

        if (servico.getPecas() == null) {
            servico.setPecas(new ArrayList<>());
        }

        for (ServicoPeca peca : servico.getPecas()) {
            peca.setServico(servico);

            if (peca.getPrecoCusto() != null) {
                totalGasto += peca.getPrecoCusto();
            }
            if (peca.getPrecoVenda() != null) {
                totalCobradoPecas += peca.getPrecoVenda();
            }
        }

        double valorMaoObra = servico.getValorMaoObra() != null ? servico.getValorMaoObra() : 0.0;
        double valorBase = totalCobradoPecas + valorMaoObra;

        // ========= CALCULA TOTAL COM JUROS (SE CRÉDITO PARCELADO) =========
        double valorTotal = valorBase;
        FormaPagamento fp = servico.getFormaPagamento();

        if (fp == FormaPagamento.CREDITO_PARCELADO &&
                servico.getNumeroParcelas() != null &&
                servico.getNumeroParcelas() > 1 &&
                servico.getJurosPercentual() != null &&
                servico.getJurosPercentual() > 0.0) {

            double juros = servico.getJurosPercentual() / 100.0;
            valorTotal = valorBase * (1.0 + juros);
        }

        servico.setValorGasto(totalGasto);
        servico.setValorTotal(valorTotal);

        // ========= VINCULA PARCELAS AO SERVIÇO (SE TIVER) =========
        if (servico.getParcelas() == null) {
            servico.setParcelas(new ArrayList<>());
        }

        for (Parcela parcela : servico.getParcelas()) {
            parcela.setServico(servico);
            if (parcela.getPago() == null) {
                parcela.setPago(false);
            }
        }

        // ========= STATUS PADRÃO =========
        if (servico.getStatus() == null || servico.getStatus().isBlank()) {
            servico.setStatus("EM_ANDAMENTO");
        }

        return servicoRepository.save(servico);
    }

    @Transactional
    public Servico atualizarStatus(UUID id, String novoStatus) {
        Servico servico = servicoRepository.findById(id).orElseThrow();
        servico.setStatus(novoStatus);
        return servicoRepository.save(servico);
    }

    @Transactional
    public Servico atualizarParcelas(UUID id, List<Parcela> novasParcelas) {
        Servico servico = servicoRepository.findById(id).orElseThrow();

        // limpa antigas
        servico.getParcelas().clear();

        // adiciona novas
        for (Parcela p : novasParcelas) {
            p.setServico(servico);
            if (p.getPago() == null) {
                p.setPago(false);
            }
            servico.getParcelas().add(p);
        }

        return servicoRepository.save(servico);
    }

    @Transactional
    public void excluir(UUID id) {
        servicoRepository.deleteById(id);
    }
}
