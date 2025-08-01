package com.treina.recife.sgp.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.treina.recife.sgp.model.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpfAndEmail(String cpf, String email);

    List<Usuario> findByNomeContains(String nome);

    List<Usuario> findByNomeLike(String nome);

    boolean existsByEmail(String email);
   
    
        


    
        
}

