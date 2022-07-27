package com.dong.todo.dto;

import com.dong.todo.domain.Card;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDtoRequest {

    private String id;
    private String title;
    private boolean done;

    public CardDtoRequest(final Card card) {
        card.getId();
    }

    public Card toEntity() {
        return new Card();
    }
}
