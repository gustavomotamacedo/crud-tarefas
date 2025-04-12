package org.gusmacedo.todo_list.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.gusmacedo.todo_list.controller.dto.TarefaDTO;
import org.gusmacedo.todo_list.entity.Tarefa;
import org.gusmacedo.todo_list.service.TarefaService;
import org.gusmacedo.todo_list.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tarefas")
@Tag(name = "Tarefas", description = "API para gerenciamento de tarefas de usuários") // Documentação geral do controller [[7]]
public class TarefaController {

    @Autowired
    public final UsuarioService usuarioService;

    @Autowired
    public final TarefaService tarefaService;

    public TarefaController(UsuarioService usuarioService, TarefaService tarefaService) {
        this.tarefaService = tarefaService;
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Atualiza uma tarefa existente",
            description = "Atualiza a descrição de uma tarefa específica pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao atualizar a tarefa", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {
        try {
            tarefaService.atualizarTarefa(id, usuarioService.getUsuarioId(), tarefaDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("Error", e.getMessage()).build();
        }
    }

    @Operation(
            summary = "Deleta uma tarefa existente",
            description = "Remove uma tarefa específica pelo ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        try {
            tarefaService.deletarTarefa(id, usuarioService.getUsuarioId());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().header("Error", e.getMessage()).build();
        }
    }

    @Operation(
            summary = "Cadastra uma nova tarefa",
            description = "Cadastra uma nova tarefa para o usuário autenticado.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TarefaDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao cadastrar a tarefa", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        tarefaService.cadastrarTarefa(tarefaDTO.toTarefa(usuarioService.getUsuarioById(usuarioService.getUsuarioId())));
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Lista todas as tarefas do usuário",
            description = "Retorna a lista de tarefas associadas ao usuário autenticado.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de tarefas retornada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                            schema = @Schema(implementation = TarefaDTO.class)
                                    ))),
                    @ApiResponse(responseCode = "400", description = "Erro ao consultar as tarefas", content = @Content(schema = @Schema(hidden = true)))
            }
    )
    @GetMapping
    public List<TarefaDTO> consultarTarefas() {
        try {
            return tarefaService.consultarTarefas(usuarioService.getUsuarioId());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}