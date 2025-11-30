package com.sabinomecanica.backend.controllers;

import com.sabinomecanica.backend.models.Parcela;
import com.sabinomecanica.backend.models.Servico;
import com.sabinomecanica.backend.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    public List<Servico> listar() {
        return servicoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Servico buscar(@PathVariable UUID id) {
        return servicoService.buscarPorId(id);
    }

    @PostMapping
    public Servico criar(@RequestBody Servico servico) {
        // POST sempre cria um novo serviço
        return servicoService.salvar(servico);
    }

    @PutMapping("/{id}")
    public Servico editar(@PathVariable UUID id, @RequestBody Servico servico) {
        // garante que está atualizando o certo
        servico.setId(id);
        return servicoService.salvar(servico);
    }

    @PutMapping("/{id}/status")
    public Servico atualizarStatus(@PathVariable UUID id, @RequestBody StatusDTO dto) {
        return servicoService.atualizarStatus(id, dto.getStatus());
    }

    @PutMapping("/{id}/parcelas")
    public Servico atualizarParcelas(@PathVariable UUID id, @RequestBody List<Parcela> parcelas) {
        return servicoService.atualizarParcelas(id, parcelas);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        servicoService.excluir(id);
    }

    // DTO simples só para o status
    public static class StatusDTO {
        private String status;

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
    }
}
