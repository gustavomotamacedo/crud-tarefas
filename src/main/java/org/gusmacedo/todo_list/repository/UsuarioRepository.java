package org.gusmacedo.todo_list.repository;

import org.gusmacedo.todo_list.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
