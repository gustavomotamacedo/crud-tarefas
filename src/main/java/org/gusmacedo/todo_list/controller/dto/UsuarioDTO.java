package org.gusmacedo.todo_list.controller.dto;

import org.gusmacedo.todo_list.entity.Usuario;

public record UsuarioDTO(
        String nome,
        String email,
        String senha
) {
    public Usuario toUsuario() {
        return new Usuario(nome, email, senha);
    }
}
