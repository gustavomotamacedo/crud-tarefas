package org.gusmacedo.todo_list.repository;

import org.gusmacedo.todo_list.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Optional<Tarefa>> findByUsuarioId(Long usuarioId);
}
