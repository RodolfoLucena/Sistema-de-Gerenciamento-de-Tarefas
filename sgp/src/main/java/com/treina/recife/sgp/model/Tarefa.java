package com.treina.recife.sgp.model;

import java.io.Serializable;
import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.treina.recife.sgp.constants.Prioridade;
import com.treina.recife.sgp.constants.StatusTarefas;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TAREFA")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taskId")
    private long taskId;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "dataCriacao", nullable = false)
    private LocalDate dataCriacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "dataConclusao")
    private LocalDate dataConclusao;

    @Column(name = "PRIORIDADE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusTarefas status;

    @ManyToOne
    @JoinColumn(name = "projectId", referencedColumnName = "projectId" , nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private Usuario usuario;


}
