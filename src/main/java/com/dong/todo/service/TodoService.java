package com.dong.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public String test() {
        Todo todo = Todo.builder().id("98").title("ter").userId("99").build();
        todoRepository.save(todo);
        return "OK";
    }


    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Todo toEntity) {
        return toEntity;
    }

    public void delete(String id) {
        //소델로 구현
    }
}
