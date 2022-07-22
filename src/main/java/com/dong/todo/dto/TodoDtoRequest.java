package com.dong.todo.dto;

import io.dong.westsea.todo.domain.Todo;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDtoRequest {

    private String id;
    private String title;
    private boolean done;

    public TodoDtoRequest(final Todo todo) {
         todo.getId();
    }

    public Todo toEntity() {
        return new Todo();
    }
}
