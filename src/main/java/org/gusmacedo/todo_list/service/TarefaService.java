package org.gusmacedo.todo_list.service;

import org.gusmacedo.todo_list.controller.dto.TarefaDTO;
import org.gusmacedo.todo_list.entity.Tarefa;
import org.gusmacedo.todo_list.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaDTO> consultarTarefas(Long usuarioId) {
        List<Optional<Tarefa>> tarefas = tarefaRepository.findByUsuarioId(usuarioId);

        if (tarefas.isEmpty()) {
            throw new IllegalArgumentException("Não há tarefas para este usuário.");
        }

        return tarefas.stream().map(t -> new TarefaDTO(t.get().getDescricao(), t.get().getStatus())).toList();
    }
    public void cadastrarTarefa(Tarefa tarefa) {
        tarefaRepository.save(tarefa);
    }
}
