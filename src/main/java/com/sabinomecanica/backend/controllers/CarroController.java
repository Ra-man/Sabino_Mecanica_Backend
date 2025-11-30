package com.sabinomecanica.backend.controllers;

import com.sabinomecanica.backend.models.Carro;
import com.sabinomecanica.backend.models.Cliente;
import com.sabinomecanica.backend.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "http://localhost:5173") // ajusta se o front estiver em outra porta
public class CarroController {

    @Autowired
    private CarroService carroService;

    // GET /carros -> lista todos
    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos() {
        List<Carro> carros = carroService.buscarTodos();
        return ResponseEntity.ok(carros);
    }

    // GET /carros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable UUID id) {
        return carroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /carros -> cadastra novo
    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Map<String, Object> body) {
        try {
            Carro carro = montarCarroAPartirDoBody(body, null);
            Carro salvo = carroService.salvar(carro);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT /carros/{id} -> atualiza
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable UUID id,
                                       @RequestBody Map<String, Object> body) {
        return carroService.buscarPorId(id)
                .map(carroExistente -> {
                    try {
                        Carro carroAtualizado = montarCarroAPartirDoBody(body, carroExistente);
                        Carro salvo = carroService.salvar(carroAtualizado);
                        return ResponseEntity.ok(salvo);
                    } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /carros/{id} -> excluir com regra (não pode se tiver serviço)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable UUID id) {
        try {
            carroService.excluir(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ===== MÉTODO PRIVADO PRA MONTAR CARRO A PARTIR DO JSON DO FRONT =====
    private Carro montarCarroAPartirDoBody(Map<String, Object> body, Carro carroExistente) {
        // Se for null, é um novo; se não, estamos editando
        Carro carro = (carroExistente != null) ? carroExistente : new Carro();

        String placa = (String) body.get("placa");
        String modelo = (String) body.get("modelo");
        String marca = (String) body.getOrDefault("marca", null);
        String cor = (String) body.getOrDefault("cor", null);
        String fotoUrl = (String) body.getOrDefault("fotoUrl", null);
        Object clienteIdObj = body.get("clienteId");

        if (placa == null || placa.isBlank() || modelo == null || modelo.isBlank()) {
            throw new RuntimeException("Placa e modelo são obrigatórios.");
        }

        if (clienteIdObj == null) {
            throw new RuntimeException("clienteId é obrigatório para definir o dono do carro.");
        }

        UUID clienteId;
        try {
            clienteId = UUID.fromString(clienteIdObj.toString());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("clienteId inválido.");
        }

        // Busca o cliente
        Cliente cliente = carroService.buscarClienteOuErro(clienteId);

        carro.setPlaca(placa);
        carro.setModelo(modelo);
        carro.setMarca(marca);
        carro.setCor(cor);
        carro.setFotoUrl(fotoUrl);
        carro.setCliente(cliente);

        return carro;
    }
}
