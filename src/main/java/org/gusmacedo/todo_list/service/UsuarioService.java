package org.gusmacedo.todo_list.service;

import org.gusmacedo.todo_list.entity.Usuario;
import org.gusmacedo.todo_list.exeptions.SenhaIncorretaExeption;
import org.gusmacedo.todo_list.exeptions.UsuarioNaoEncontradoExeption;
import org.gusmacedo.todo_list.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void loginUsuario(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoExeption("Usuário não encontrado.");
        }
        if (!usuario.get().getSenha().equals(senha)) {
            throw new SenhaIncorretaExeption("Senha incorreta.");
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente.isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com este email.");
        }

        usuarioRepository.save(usuario);
    }

    public Long getUsuarioIdByEmail(String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoExeption("Usuário não encontrado.");
        }

        return usuario.get().getId();
    }
}
