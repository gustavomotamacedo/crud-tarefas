package org.gusmacedo.todo_list.controller.dto;

import org.gusmacedo.todo_list.entity.Status;
import org.gusmacedo.todo_list.entity.Tarefa;
import org.gusmacedo.todo_list.entity.Usuario;

public record TarefaDTO(
        String descricao,
        Status.Values status
) {

    public TarefaDTO(String descricao, Status status) {
        this(descricao, Status.Values.PENDENTE);
    }

    public Tarefa toTarefa(Usuario usuario) {
        return new Tarefa(descricao, Status.Values.PENDENTE.toStatus(), usuario);
    }
}
