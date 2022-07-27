package com.dong.todo.repository;

import com.dong.todo.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardAdvRepository extends JpaRepository<Card, Long> {
    //@Query("select Card from Card c where c.userId = ?1 ")
    //List<Card> findByUser(Long userId);
}
