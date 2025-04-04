package org.gusmacedo.todo_list.controller;

import org.gusmacedo.todo_list.controller.dto.UsuarioDTO;
import org.gusmacedo.todo_list.entity.Usuario;
import org.gusmacedo.todo_list.exeptions.SenhaIncorretaExeption;
import org.gusmacedo.todo_list.exeptions.UsuarioNaoEncontradoExeption;
import org.gusmacedo.todo_list.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("login")
    public ResponseEntity<Usuario> loginUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        try {
            usuarioService.loginUsuario(email, senha);
            return ResponseEntity.ok().build();
        } catch (UsuarioNaoEncontradoExeption e) {
           return ResponseEntity.notFound().build();
        } catch (SenhaIncorretaExeption e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.cadastrarUsuario(usuarioDTO.nome(),
                    usuarioDTO.email(),
                    usuarioDTO.senha());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
