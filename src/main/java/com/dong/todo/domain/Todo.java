package com.dong.todo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String title;
    private Long rank;
    private boolean done;
    private boolean deleted;


    public void update(Todo todo) {
        this.userId = todo.userId;
        this.title = todo.title;
        this.rank = todo.rank;
        this.done = todo.done;
        this.deleted = todo.deleted;
    }

    public void delete() {
        this.deleted = true;
    }
}
