package org.gusmacedo.todo_list.controller;

import org.gusmacedo.todo_list.controller.dto.TarefaDTO;
import org.gusmacedo.todo_list.entity.Tarefa;
import org.gusmacedo.todo_list.service.TarefaService;
import org.gusmacedo.todo_list.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/tarefas")
public class TarefaController {

    @Autowired
    public final UsuarioService usuarioService;

    @Autowired
    public final TarefaService tarefaService;

    public TarefaController(UsuarioService usuarioService, TarefaService tarefaService) {
        this.tarefaService = tarefaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody TarefaDTO tarefaDTO) {
        tarefaService.cadastrarTarefa(tarefaDTO.toTarefa(usuarioService.getUsuarioById(usuarioService.getUsuarioId())));
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public List<TarefaDTO> consultarTarefas() {
        try {
            return tarefaService.consultarTarefas(usuarioService.getUsuarioId());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
