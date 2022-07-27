package com.dong.todo.connect;

import com.dong.todo.dto.TodoDtoRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TodoControllerV1 {

    @GetMapping
    public Object create(@RequestBody TodoDtoRequest todoDtoRequest) {
        return null;
    }

    @GetMapping
    public Object read() {
        return null;
    }

    @GetMapping
    public Object write() {
        return null;
    }

    @GetMapping
    public Object delete() {
        return null;
    }


}
