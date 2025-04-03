package org.gusmacedo.todo_list.repository;

import org.gusmacedo.todo_list.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
