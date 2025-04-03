package org.gusmacedo.todo_list.repository;

import org.gusmacedo.todo_list.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
