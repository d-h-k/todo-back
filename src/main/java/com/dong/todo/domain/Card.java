package com.dong.todo.domain;

import lombok.*;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private String title;
    private Long rank;
    private boolean done;
    private boolean deleted;


    public void update(Card card) {
        this.userId = card.userId;
        this.title = card.title;
        this.rank = card.rank;
        this.done = card.done;
        this.deleted = card.deleted;
    }

    public void delete() {
        this.deleted = true;
    }
}
