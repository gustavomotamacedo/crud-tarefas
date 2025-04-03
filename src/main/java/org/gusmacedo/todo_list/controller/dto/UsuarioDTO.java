package org.gusmacedo.todo_list.controller.dto;

public record UsuarioDTO(
        String nome,
        String email,
        String senha
) {
}
