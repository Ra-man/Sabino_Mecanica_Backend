// src/main/java/com/sabinomecanica/backend/controllers/ClienteController.java
package com.sabinomecanica.backend.controllers;

import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:5173")   // <<< LIBERA O FRONT
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente criar(@RequestBody Cliente cliente) {
        // deixa o ID nulo pra JPA gerar
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable("id") String id,
                             @RequestBody Cliente cliente) {
        cliente.setId(java.util.UUID.fromString(id));
        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") String id) {
        clienteRepository.deleteById(java.util.UUID.fromString(id));
    }
}
