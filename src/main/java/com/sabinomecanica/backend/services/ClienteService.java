package com.sabinomecanica.backend.services;

import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// Camada de regra de negócio do Cliente
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Lista todos os clientes
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    // Busca por ID
    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepository.findById(id);
    }

    // Salva novo cliente
    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualiza cliente existente
    @Transactional
    public Optional<Cliente> atualizar(UUID id, Cliente dadosAtualizados) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(dadosAtualizados.getNome());
            cliente.setTelefone(dadosAtualizados.getTelefone());
            cliente.setCpf(dadosAtualizados.getCpf());
            cliente.setSituacao(dadosAtualizados.getSituacao());
            return clienteRepository.save(cliente);
        });
    }

    // Exclui cliente SOMENTE se não tiver carros vinculados
    @Transactional
    public void excluir(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        // Se tiver carros, não exclui
        if (cliente.getCarros() != null && !cliente.getCarros().isEmpty()) {
            throw new RuntimeException("Não é possível excluir: cliente possui carros vinculados.");
        }

        clienteRepository.delete(cliente);
    }
}
