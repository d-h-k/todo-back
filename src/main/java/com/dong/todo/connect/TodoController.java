package com.dong.todo.connect;

import com.dong.common.ApiResultWrapper;
import com.dong.todo.domain.Todo;
import com.dong.todo.dto.TodoDtoRequest;
import com.dong.todo.dto.TodoDtoResponse;
import com.dong.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dong.common.ApiResultWrapper.wrapOk;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable String id) {
        TodoDtoResponse response = new TodoDtoResponse(todoService.read(id));

        return wrapOk(response)
                .jsonResponse();
    }

    @GetMapping
    public ResponseEntity<?> readMany() {
        Page<Todo> todos = todoService.readAll();

        return "hello world!";
    }

    @PostMapping
    public TodoDtoResponse create(@RequestBody TodoDtoRequest todoDtoRequest) {


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
