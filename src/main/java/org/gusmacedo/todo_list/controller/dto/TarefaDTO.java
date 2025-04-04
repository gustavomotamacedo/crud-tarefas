package org.gusmacedo.todo_list.controller.dto;

public record TarefaDTO(
        String nome,
        String descricao,
        Long statusId,
        Long usuarioId
) {
}
