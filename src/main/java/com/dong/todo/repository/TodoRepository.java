package com.dong.todo.repository;

import com.dong.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
//Repository

    Optional<Todo> findById(Long id);

}
