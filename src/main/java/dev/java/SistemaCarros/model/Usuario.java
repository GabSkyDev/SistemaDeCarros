package dev.java.SistemaCarros.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;
    private String cpf;
    private String nome;
    private String email;
    private String telefone;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carro> carrosRegistrados;

    public Usuario() {}

    public Usuario(Long id_usuario, String cpf, String nome, String email, String telefone, List<Carro> carrosRegistrados) {
        this.id_usuario = id_usuario;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.carrosRegistrados = carrosRegistrados;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
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
