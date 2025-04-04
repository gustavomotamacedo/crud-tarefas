package org.gusmacedo.todo_list.exeptions;

public class SenhaIncorretaExeption extends RuntimeException {
    public SenhaIncorretaExeption(String message) {
        super(message);
    }
}
