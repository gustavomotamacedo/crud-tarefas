package org.gusmacedo.todo_list.exeptions;

public class UsuarioNaoEncontradoExeption extends RuntimeException {

    public UsuarioNaoEncontradoExeption(String message) {
        super(message);
    }
}
