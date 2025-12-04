package com.sabinomecanica.backend.services;

import com.sabinomecanica.backend.models.Servico;
import com.sabinomecanica.backend.models.ServicoPeca;
import com.sabinomecanica.backend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    // ========================
    // LISTAR TODOS
    // ========================
    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    // ========================
    // BUSCAR POR ID
    // ========================
    public Servico buscarPorId(UUID id) {
        return servicoRepository.findById(id).orElse(null);
    }

    // ========================
    // SALVAR / ATUALIZAR
    // ========================
    @Transactional
    public Servico salvar(Servico servico) {

        // 🔴 ATENÇÃO AQUI:
        // Use o NOME DO CAMPO que está na tua entidade Servico.
        // Se em Servico.java estiver "private List<ServicoPeca> pecas;",
        // o getter é getPecas().
        if (servico.getPecas() != null) {
            for (ServicoPeca item : servico.getPecas()) {
                // garante o vínculo da peça com o serviço
                item.setServico(servico);
            }
        }

        // Se tiver campos de valor na entidade Servico, dá pra somar aqui:
        /*
        double totalGasto = 0.0;
        double totalCobradoPecas = 0.0;

        if (servico.getPecas() != null) {
            for (ServicoPeca item : servico.getPecas()) {
                if (item.getPrecoCusto() != null) {
                    totalGasto += item.getPrecoCusto();
                }
                if (item.getPrecoVenda() != null) {
                    totalCobradoPecas += item.getPrecoVenda();
                }
            }
        }

        Double maoObra = servico.getValorMaoObra() != null ? servico.getValorMaoObra() : 0.0;
        servico.setValorGasto(totalGasto);
        servico.setValorTotal(totalCobradoPecas + maoObra);
        */

        return servicoRepository.save(servico);
    }

    // ========================
    // DELETAR
    // ========================
    public void deletar(UUID id) {
        if (servicoRepository.existsById(id)) {
            servicoRepository.deleteById(id);
        }
    }

    // ========================
    // ATUALIZAR STATUS
    // ========================
    @Transactional
    public Servico atualizarStatus(UUID id, String novoStatus) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        // Se o campo status for String na entidade:
        servico.setStatus(novoStatus);

        // Se for ENUM, algo como:
        // servico.setStatus(StatusServico.valueOf(novoStatus));

        return servicoRepository.save(servico);
    }
}
