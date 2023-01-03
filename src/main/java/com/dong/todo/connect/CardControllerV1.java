package com.dong.todo.connect;

import com.dong.todo.domain.Card;
import com.dong.todo.dto.CardDtoRequest;
import com.dong.todo.dto.CardDtoResponse;
import com.dong.todo.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.dong.common.dto.ResponseWrapper.wrapCreated;
import static com.dong.common.dto.ResponseWrapper.wrapOk;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api/card/v1")
@RequestMapping("/todo")
public class CardControllerV1 {

    private final CardService cardService;

    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable Long id) {
        CardDtoResponse response = new CardDtoResponse(cardService.read(id));
        cardService.read(id);

        return wrapOk(response)
                .jsonResponse();
    }

//    @GetMapping
//    public ResponseEntity<?> readMany() {
//        Page<Card> todos = cardService.readAll();
//
//        return wrapOk(todos)
//                .jsonResponse();
//    }


    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String userId = "1";
            //@AuthenticationPrincipal String userId) {
        System.out.println("UserID : " + userId);
        // (1) 서비스 메서드의 retrieve메서드를 사용해 Todo리스트를 가져온다
        List<Card> entities = cardService.retrieve(userId);

        // (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        // (6) 변환된 TodoDTO리스트를 이용해ResponseDTO를 초기화한다.
        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        // (7) ResponseDTO를 리턴한다.
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CardDtoRequest cardDtoRequest) {
        Card addedCard = cardService.addTodo(cardDtoRequest.toEntity());
        CardDtoResponse response = new CardDtoResponse(addedCard);

        return wrapCreated(response)
                .jsonResponse();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CardDtoRequest cardDtoRequest) {
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
