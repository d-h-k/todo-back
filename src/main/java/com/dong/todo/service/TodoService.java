package com.dong.todo.service;

import com.dong.todo.domain.Todo;
import com.dong.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public Todo read(String id) {
        //todoRepository.findById(id);
        return null;
    }

    public Page<Todo> readAll() {
        return null;
    }
}
