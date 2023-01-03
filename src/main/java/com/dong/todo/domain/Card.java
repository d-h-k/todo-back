package com.dong.todo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "card")
public class Card {

    @Id
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;


    private String title;
    private Long rank;
    private Status status;
    private boolean star;
    private boolean done;
    private boolean deleted;


    public void update(Card card) {
        this.userId = card.userId;
        this.title = card.title;
        this.rank = card.rank;
        this.status = card.status;
        this.star = card.star;
        this.done = card.done;
        this.deleted = card.deleted;
    }

    public void delete() {
        this.deleted = true;
    }

    public void unDelete() {
        this.deleted = false;
    }

    public void star() {
        this.star = true;
    }

    public void unStar() {
        this.star = false;
    }

    public void done() {
        this.star = true;
    }

    public void unDone() {
        this.star = false;
    }


}
