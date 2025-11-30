package com.sabinomecanica.backend.controllers;

import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Controlador REST para Cliente
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:5173") // porta do front (ajusta se for outra)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // GET /clientes -> lista todos
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }

    // GET /clientes/{id} -> busca um cliente
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /clientes -> cria novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente salvo = clienteService.salvar(cliente);
        return ResponseEntity.ok(salvo);
    }

    // PUT /clientes/{id} -> atualiza um cliente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID id,
                                             @RequestBody Cliente clienteAtualizado) {
        return clienteService.atualizar(id, clienteAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /clientes/{id} -> exclui cliente (se não tiver carros)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable UUID id) {
        try {
            clienteService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // volta 400 com a mensagem (ex: "cliente possui carros vinculados")
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
