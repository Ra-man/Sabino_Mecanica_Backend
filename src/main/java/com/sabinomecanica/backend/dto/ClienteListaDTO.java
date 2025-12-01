// src/main/java/com/sabinomecanica/backend/dto/ClienteListaDTO.java
package com.sabinomecanica.backend.dto;

import java.util.UUID;

public class ClienteListaDTO {

    private UUID id;
    private String nome;
    private String telefone;
    private String cpf;
    private String situacao; // String ou enum.toString()

    public ClienteListaDTO() {}

    public ClienteListaDTO(UUID id, String nome, String telefone, String cpf, String situacao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.situacao = situacao;
    }

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
}
