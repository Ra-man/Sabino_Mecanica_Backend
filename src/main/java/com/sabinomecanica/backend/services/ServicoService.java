package com.sabinomecanica.backend.services;


import com.sabinomecanica.backend.models.Servico;
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
        return servicoRepository.save(servico);
    }

    public void deletar(UUID id) {
        servicoRepository.deleteById(UUID.fromString(String.valueOf(id)));
    }
}