package com.dong.todo.connect;

import com.dong.todo.domain.Card;
import com.dong.todo.dto.CardDtoRequest;
import com.dong.todo.dto.CardDtoResponse;
import com.dong.todo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dong.common.ResponseWrapper.wrapCreated;
import static com.dong.common.ResponseWrapper.wrapOk;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2")
public class CardControllerV2 {

    private final CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        CardDtoResponse response = new CardDtoResponse(cardService.read(id));
        cardService.read(id);

        return wrapOk(response)
                .jsonResponse();
    }

    @GetMapping
    public ResponseEntity<?> readMany() {
        Page<Card> todos = cardService.readAll();

        return wrapOk(todos)
                .jsonResponse();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CardDtoRequest cardDtoRequest) {
        Card addedCard = cardService.addTodo(cardDtoRequest.toEntity());
        CardDtoResponse response = new CardDtoResponse(addedCard);

        return wrapCreated(response)
                .jsonResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CardDtoRequest cardDtoRequest) {//업데이트용 dto
        Card updatedTod = cardService.update(id, cardDtoRequest.toEntity());
        CardDtoResponse response = new CardDtoResponse(updatedTod);

        return wrapOk(response)
                .jsonResponse();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Long deleteId = cardService.delete(id);

        return wrapOk(deleteId)
                .jsonResponse();
    }

}
