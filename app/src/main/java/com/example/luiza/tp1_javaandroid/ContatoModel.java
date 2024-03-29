package com.example.luiza.tp1_javaandroid;

import java.io.Serializable;

public class ContatoModel implements Serializable {

    private String nome;
    private String telefone;
    private String email;
    private String cidade;

    public ContatoModel(String nome, String telefone, String email, String cidade) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
