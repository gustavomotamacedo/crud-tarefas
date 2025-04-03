package org.gusmacedo.todo_list.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
