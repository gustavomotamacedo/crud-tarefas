package org.gusmacedo.todo_list.service;

import org.gusmacedo.todo_list.entity.Usuario;
import org.gusmacedo.todo_list.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(String nome, String email, String senha) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(email);

        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com este email.");
        }

        usuarioRepository.save(new Usuario(nome, email, senha));
    }

}
