package com.treina.recife.sgp.controller;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treina.recife.sgp.model.Projeto;
import com.treina.recife.sgp.service.ProjetoService;
import com.treina.recife.sgp.service.UsuarioService;


@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    ProjetoService projetoService;

    @Autowired
    UsuarioService usuarioService;

    Logger logger = LogManager.getLogger(ProjetoController.class);

    public ProjetoController(ProjetoService projetoService, UsuarioService usuarioService){

        this.projetoService = projetoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<Projeto>> getProjetos(@PageableDefault(sort = "projectId", direction = Sort.Direction.ASC) Pageable pageable){
        page<Projeto> projeto = projetoService.getProjetos(pageable);

        if (condition) {
            
        }
        
    }

}
