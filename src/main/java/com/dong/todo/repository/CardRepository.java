package com.dong.todo.repository;

import com.dong.todo.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
//Repository

    Optional<Card> findById(Long id);

    @Query("select Card from Card c where c.userId = ?1 ")
    List<Card> findByUser(Long userId);
}
