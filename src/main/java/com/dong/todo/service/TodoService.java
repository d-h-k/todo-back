package com.dong.todo.service;

import com.dong.todo.domain.Todo;
import com.dong.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo todo) {
        Todo origin = todoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        origin.update(todo);
        return origin;
    }

    public Long delete(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        todo.delete();
        return todo.getId();
    }

    public Todo read(Long id) {
        //todoRepository.findById(id);
        return null;
    }

    public Page<Todo> readAll() {
        return null;
    }
}
