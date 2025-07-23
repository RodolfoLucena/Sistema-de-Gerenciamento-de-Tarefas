package com.treina.recife.sgp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.treina.recife.sgp.model.Tarefa;
import com.treina.recife.sgp.model.Projeto;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa>findByResponsavelUserId(Long userId);

    /*List<Tarefa>findByProjetoProjectId(Projeto usuario);*/

    List<Tarefa>fundByUsuarioUserId(Long userId);

    boolean existsByTitulo(String title);

    boolean existsByUsuarioUserId(Long userId);

    Optional<Projeto> findyResponsavelUserId(Long userId);


    
    

        


    

}
