package com.dong.todo.repository;

import com.dong.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoAdvRepository extends JpaRepository<Todo, String> {
    @Query("select Todo from Todo t where t.userId = ?1 ")
    List<Todo> findByUser(String userId);
}
