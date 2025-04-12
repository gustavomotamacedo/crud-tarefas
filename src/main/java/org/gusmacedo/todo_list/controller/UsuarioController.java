package org.gusmacedo.todo_list.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gusmacedo.todo_list.controller.dto.UsuarioDTO;
import org.gusmacedo.todo_list.entity.Usuario;
import org.gusmacedo.todo_list.exeptions.SenhaIncorretaExeption;
import org.gusmacedo.todo_list.exeptions.UsuarioNaoEncontradoExeption;
import org.gusmacedo.todo_list.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuarios")
@Tag(name = "Usuários", description = "API para gerenciamento de usuários") // Documentação geral do controller [[7]]
public class UsuarioController {

    private Long usuarioId;

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Realiza o login do usuário",
            description = "Autentica o usuário com base no email e senha fornecidos.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Senha incorreta"),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
            }
    )
    @PostMapping("login")
    public ResponseEntity<Usuario> loginUsuario(@RequestParam("email") String email, @RequestParam("senha") String senha) {
        try {
            usuarioService.loginUsuario(email, senha);
            usuarioService.setUsuarioId(usuarioService.getUsuarioIdByEmail(email));
            return ResponseEntity.ok().header(String.valueOf(usuarioId)).build();
        } catch (UsuarioNaoEncontradoExeption e) {
            return ResponseEntity.notFound().build();
        } catch (SenhaIncorretaExeption e) {
            return ResponseEntity.status(401).build();
        }
    }

    @Operation(
            summary = "Cadastra um novo usuário",
            description = "Registra um novo usuário no sistema com base nos dados fornecidos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao cadastrar o usuário", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.cadastrarUsuario(usuarioDTO.toUsuario());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}