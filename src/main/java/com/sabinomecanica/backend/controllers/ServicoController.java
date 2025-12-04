package com.sabinomecanica.backend.controllers;

import com.sabinomecanica.backend.models.Servico;
import com.sabinomecanica.backend.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/servicos")  // CASA COM O FRONT: /servicos
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // LISTAR TODOS
    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> lista = servicoService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable UUID id) {
        // AQUI eu assumo que teu service retorna Servico ou null
        Servico servico = servicoService.buscarPorId(id);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servico);
    }

    // CRIAR
    @PostMapping
    public ResponseEntity<Servico> criar(@RequestBody Servico servico) {
        Servico salvo = servicoService.salvar(servico);
        return ResponseEntity.ok(salvo);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable UUID id,
                                             @RequestBody Servico servico) {
        servico.setId(id);
        Servico atualizado = servicoService.salvar(servico);
        return ResponseEntity.ok(atualizado);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // ATUALIZAR STATUS (CONCLUIR SERVIÇO)
    @PutMapping("/{id}/status")
    public ResponseEntity<Servico> atualizarStatus(@PathVariable UUID id,
                                                   @RequestBody StatusDTO dto) {
        Servico atualizado = servicoService.atualizarStatus(id, dto.getStatus());
        return ResponseEntity.ok(atualizado);
    }

    // DTO SIMPLES PRO STATUS
    public static class StatusDTO {
        private String status;

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
