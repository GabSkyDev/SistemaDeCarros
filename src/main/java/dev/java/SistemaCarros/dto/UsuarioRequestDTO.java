package dev.java.SistemaCarros.dto;

import dev.java.SistemaCarros.model.Carro;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRequestDTO {
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    private List<Carro> carrosRegistrados = new ArrayList<>();
    public UsuarioRequestDTO(){

    }
    public UsuarioRequestDTO(Long id, String cpf, String nome, String email, String telefone) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Carro> getCarrosRegistrados() {
        return carrosRegistrados;
    }

    public void setCarrosRegistrados(List<Carro> carrosRegistrados) {
        this.carrosRegistrados = carrosRegistrados;
    }

}
