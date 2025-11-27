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

    // Lista todos os serviços
    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    // Busca por ID
    public Optional<Servico> buscarPorId(UUID id) {
        return servicoRepository.findById(id);
    }

    // Salva (criar/atualizar) o serviço + peças
    @Transactional
    public Servico salvar(Servico servico) {

        double totalPecasCobradas = 0.0;

        // Garante o vínculo das peças com o serviço
        if (servico.getItens() != null) {
            for (ServicoPeca item : servico.getItens()) {
                // ESSENCIAL: seta o serviço dono do item (preenche id_servico)
                item.setServico(servico);
                totalPecasCobradas += item.getPrecoCobrado();
            }
        }

        // Calcula o valor_total no BACK (mais seguro que confiar no front)
        double maoObra = servico.getPreco_mao_obra();
        servico.setValor_total(totalPecasCobradas + maoObra);

        // Salva serviço + itens em cascata
        return servicoRepository.save(servico);
    }

    // Deleta por ID
    public void deletar(UUID id) {
        servicoRepository.deleteById(id);
    }
}