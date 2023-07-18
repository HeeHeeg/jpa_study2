package com.example.jpa_day2.todos.repository;

import com.example.jpa_day2.todos.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
