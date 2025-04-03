package org.gusmacedo.todo_list.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_status")
public class Status {

    @Id
    private Long id;
    private String nome;

    public Status() {
    }

    public Status(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public enum Values {
        PENDENTE(1L, "Pendente"),
        CONCLUIDO(2L, "Concluído");

        private Long id;
        private String nome;

        Values(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Status toStatus() {
            return new Status(id, nome);
        }

    }
}
