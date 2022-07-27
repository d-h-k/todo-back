package com.dong.todo.connect;

import com.dong.todo.dto.CardDtoRequest;
import com.dong.todo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CardControllerV1 {

    private final CardService cardService;

    @PostMapping
    public Object create(@RequestBody CardDtoRequest cardDtoRequest) {
        return cardService.addTodo(cardDtoRequest.toEntity());
    }

    @GetMapping("/{id}")
    public Object read(@PathVariable Long id) {
        return cardService.read(id);
    }

    @GetMapping("/{id}")
    public Object write(@PathVariable Long id, @RequestBody CardDtoRequest cardDtoRequest) {
        cardService.update(id, cardDtoRequest.toEntity());
        return null;
    }

    @PutMapping("/{id}")
    public Object delete(@PathVariable Long id) {
        return cardService.delete(id);
    }


}
