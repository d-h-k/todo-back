package com.dong.todo.connect;

import com.dong.todo.domain.Todo;
import com.dong.todo.dto.TodoDtoRequest;
import com.dong.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class TodoControllerV1 {

    private final TodoService todoService;

    @PostMapping
    public Object create(@RequestBody TodoDtoRequest todoDtoRequest) {
        return todoService.addTodo(todoDtoRequest.toEntity());
    }

    @GetMapping("/{id}")
    public Object read(@PathVariable Long id) {
        return todoService.read(id);
    }

    @GetMapping("/{id}")
    public Object write(@PathVariable Long id,@RequestBody TodoDtoRequest todoDtoRequest) {
        todoService.update(id, todoDtoRequest.toEntity());
        return null;
    }

    @PutMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return todoService.delete(id);
    }


}
