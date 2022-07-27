package com.dong.todo.service;

import com.dong.todo.domain.Card;
import com.dong.todo.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;

    public Card addTodo(Card card) {
        return cardRepository.save(card);
    }

    public Card update(Long id, Card card) {
        Card originCard = cardRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        originCard.update(card);
        return originCard;
    }

    public Long delete(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        card.delete();
        return card.getId();
    }

    public Card read(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public Page<Card> readAll() {
        return null;
    }
}
