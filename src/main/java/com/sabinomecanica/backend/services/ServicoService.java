package com.sabinomecanica.backend.services;


import com.sabinomecanica.backend.models.Servico;
import com.sabinomecanica.backend.models.ServicoPeca;
import com.sabinomecanica.backend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    public Optional<Servico> buscarPorId(UUID id) {
        return servicoRepository.findById(id);
    }

    @Transactional
    public Servico salvar(Servico servico) {

        double totalPecasCobradas = 0.0;

        // Garante o vínculo das peças com o serviço
        if (servico.getItens() != null) {
            for (ServicoPeca item : servico.getItens()) {
                item.setServico(servico);            // <<< ESSA LINHA É OBRIGATÓRIA
                totalPecasCobradas += item.getPrecoCobrado();
            }
        }

        // Calcula o valor_total no BACK (mais seguro)
        double maoObra = servico.getPreco_mao_obra();
        servico.setValor_total(totalPecasCobradas + maoObra);

        return servicoRepository.save(servico);
    }

    public void deletar(UUID id) {
        servicoRepository.deleteById(UUID.fromString(String.valueOf(id)));
    }
}