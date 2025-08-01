package com.treina.recife.sgp.dto;

import java.io.ObjectInputFilter.Status;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.treina.recife.sgp.constants.StatusUsuario;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {
    private long userId;

    private String nome;

    private String cpf;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNAscimanto() {
        return dataNAscimanto;
    }

    public void setDataNAscimanto(LocalDate dataNAscimanto) {
        this.dataNAscimanto = dataNAscimanto;
    }

    public StatusUsuario getStatus() {
        return status;
    }

    public void setStatus(StatusUsuario status) {
        this.status = status;
    }

    private String email;

    private String senha;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNAscimanto;

    private StatusUsuario status;

}
