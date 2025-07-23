package com.treina.recife.sgp.controller;


import com.treina.recife.sgp.constants.StatusUsuario;
import com.treina.recife.sgp.dto.UsuarioDto;
import com.treina.recife.sgp.model.Usuario;
import com.treina.recife.sgp.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    Logger logger = LogManager.getLogger(UsuarioController.class);

    public UsuarioController(UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<Usuario>> getUsuarios(@PageableDefault(sort = "userId",
            direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Usuario> usuarios = usuarioService.getUsuarios(pageable);

        if (usuarios.isEmpty()) {
            logger.info("Ainda não há usuários cadastrados.");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Page.empty());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable(value = "usuarioId") long usuarioId) {

        Optional<Usuario> usuario = usuarioService.getUsuarioById(usuarioId);

        if (usuario.isEmpty()) {
            logger.error("Usuário não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        } else {
            logger.info(usuario.get().toString());
            return ResponseEntity.status(HttpStatus.OK).body(usuario.get());
        }

    }

    @PostMapping()
    public ResponseEntity<Object>createUsuario(@RequestBody UsuarioDto usuarioDto){

    boolean emailAlreadyTaken = usuarioService.isEmailAlreadyTaken(usuarioDto.getEmail());

        if (emailAlreadyTaken) {
            logger.error("{} já está em uso", usuarioDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(usuarioDto.getEmail() + "já está e uso");
        }else{
            Usuario novoUsuario = new Usuario();
            novoUsuario.setNome(usuarioDto.getNome());
            novoUsuario.setCpf(usuarioDto.getCpf());
            novoUsuario.setEmail(usuarioDto.getEmail());
            novoUsuario.setSenha(usuarioDto.getSenha());
            novoUsuario.setDataNascimento(usuarioDto.getDataNAscimanto());
            novoUsuario.setStatus(StatusUsuario.ATIVO);

            usuarioService.createUsuario(novoUsuario);

            logger.info("Usuario {} criado com sucesso", novoUsuario.getUserId());

            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
            }


    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object>deleteUsuario(@PathVariable(value = "userID") long userId) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);
        
        if (usuario.isEmpty()) {
            logger.warn("Usuario não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado"); 
        }else{
            usuarioService.deleteUsuario(userId);
            logger.info("Usuario {} deletado com sucesso", userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuario deletado com sucesso");

        }
        
        
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object>updateUsusario(@PathVariable(value = "userId") long userId, @RequestBody UsuarioDto usuarioDto){

        Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);

        if (usuario.isEmpty()) {
            logger.error("Usuario não econtrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
            
        }else{
            Usuario usuarioAtualizado = usuario.get();
            usuarioAtualizado.setNome(usuarioDto.getNome());
            usuarioAtualizado.setCpf(usuarioDto.getCpf());
            usuarioAtualizado.setEmail(usuarioDto.getEmail());
            usuarioAtualizado.setDataNascimento(usuarioDto.getDataNAscimanto());
            usuarioAtualizado.setStatus(usuarioDto.getStatus());

            usuarioService.updateUsuario(usuarioAtualizado);

            logger.info("Usuario de ID {} atualizado com sucesso", usuarioAtualizado.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
        }

    }

    @PatchMapping("/{userId}/status")
    public ResponseEntity<Object> atualizarStatus(@PathVariable(value = "userId") long userId, @RequestBody Map<String, String> body){

        Optional<Usuario> usuario = usuarioService.getUsuarioById(userId);

        if (usuario.isEmpty()) {
            logger.error("Usuario não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
                        
        }

        String statusBody = body.get("Status");

        if (statusBody == null) {
            return ResponseEntity.badRequest().body("Status é obrigatorio");
            
        }

        StatusUsuario novoStatus;

        try{
            novoStatus = StatusUsuario.valueOf(statusBody.toUpperCase());

        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body("Status invalido valores permitidos: ATIVO, INATIVO");

        }

        Usuario usuarioAtualizado = usuario.get();
        usuarioAtualizado.setStatus(novoStatus);

        usuarioService.updateUsuario(usuarioAtualizado);
        logger.info("Status do usuario atualizado com sucesso.");

        return ResponseEntity.status(HttpStatus.OK).body(usuarioAtualizado);
        


    }

    

}
