package com.sabinomecanica.backend.services;

import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Método para buscar todos os clientes
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    // Método para buscar um cliente por CPF
    public Optional<Cliente> buscarPorCpf(UUID id) {
        return clienteRepository.findById(id);
    }

    // Método para salvar/atualizar um cliente
    @Transactional
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Método para deletar
    public void deletar(UUID id) {
        clienteRepository.deleteById(id);
    }
}
