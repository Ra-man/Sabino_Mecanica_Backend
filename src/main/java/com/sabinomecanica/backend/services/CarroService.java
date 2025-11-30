package com.sabinomecanica.backend.services;

import com.sabinomecanica.backend.models.Carro;
import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.repositories.CarroRepository;
import com.sabinomecanica.backend.repositories.ClienteRepository;
import com.sabinomecanica.backend.repositories.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    // LISTAR
    public List<Carro> buscarTodos() {
        // Aqui o Carro já vem com cliente no JSON (cliente tem @JsonIgnore nos carros),
        // então não dá loop infinito.
        return carroRepository.findAll();
    }

    // BUSCAR POR ID
    public Optional<Carro> buscarPorId(UUID id) {
        return carroRepository.findById(id);
    }

    // CRIAR / ATUALIZAR (dados já montados no controller)
    @Transactional
    public Carro salvar(Carro carro) {
        return carroRepository.save(carro);
    }

    // EXCLUIR COM REGRA: SÓ SE NÃO TIVER SERVIÇO
    @Transactional
    public void excluir(UUID id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado."));

        boolean temServico = servicoRepository.existsByCarro_Id(carro.getId());

        if (temServico) {
            throw new RuntimeException("Não é possível excluir: o carro possui serviços vinculados.");
        }

        carroRepository.delete(carro);
    }

    // BUSCA O CLIENTE PELO ID (usado no controller pra montar o dono)
    public Cliente buscarClienteOuErro(UUID clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o ID informado."));
    }
}
