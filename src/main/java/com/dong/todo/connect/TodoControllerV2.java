package com.dong.todo.connect;

import com.dong.todo.domain.Todo;
import com.dong.todo.dto.TodoDtoRequest;
import com.dong.todo.dto.TodoDtoResponse;
import com.dong.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dong.common.ResponseWrapper.wrapCreated;
import static com.dong.common.ResponseWrapper.wrapOk;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2")
public class TodoControllerV2 {

    private final TodoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        TodoDtoResponse response = new TodoDtoResponse(todoService.read(id));
        todoService.read(id);
        return wrapOk(response)
                .jsonResponse();
    }

    @GetMapping
    public ResponseEntity<?> readMany() {
        Page<Todo> todos = todoService.readAll();

        return wrapOk(todos)
                .jsonResponse();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TodoDtoRequest todoDtoRequest) {
        Todo addedTodo = todoService.addTodo(todoDtoRequest.toEntity());
        TodoDtoResponse response = new TodoDtoResponse(addedTodo);

        return wrapCreated(response)
                .jsonResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TodoDtoRequest todoDtoRequest) {//업데이트용 dto
        Todo updatedTod = todoService.update(id, todoDtoRequest.toEntity());
        TodoDtoResponse response = new TodoDtoResponse(updatedTod);

        return wrapOk(response)
                .jsonResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long deleteId = todoService.delete(id);

        return wrapOk(deleteId)
                .jsonResponse();
    }

}
