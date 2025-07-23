package com.treina.recife.sgp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.treina.recife.sgp.model.Tarefa;
import com.treina.recife.sgp.model.Projeto;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


    List<Tarefa> findByProjetoProjectId(long projectId);

    List<Tarefa> findByUsuarioUserId(Long userId);

    boolean existsByTitulo(String title);

    boolean existsByUsuarioUserId(Long userId);



    
    

        


    

}
