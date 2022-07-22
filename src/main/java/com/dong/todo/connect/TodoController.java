package com.dong.todo.connect;

import io.dong.westsea.todo.domain.Todo;
import io.dong.westsea.todo.dto.TodoDtoRequest;
import io.dong.westsea.todo.dto.TodoDtoResponse;
import io.dong.westsea.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    private final TodoService todoService;

    @SuppressWarnings("이런짓좀 제발 하지 말라는 의미에서 남겨줌")
    @GetMapping
    public String antiPatternSample(){
        return "hello world!";
    }

    @PostMapping
    public TodoDtoResponse create(@RequestBody TodoDtoRequest todoDtoRequest){


        Todo todo = todoService.createTodo(todoDtoRequest.toEntity());
        return new TodoDtoResponse(todo);
    }

    @PutMapping
    public void update(@RequestBody TodoDtoRequest todoDtoRequest) {//업데이트용 dto
        todoService.update(todoDtoRequest.toEntity());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        todoService.delete(id);

    }

}
