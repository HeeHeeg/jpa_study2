package com.example.jpa_day2.todos.service;

import com.example.jpa_day2.todos.domain.request.TodoRequest;
import com.example.jpa_day2.todos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void insert(TodoRequest request) {
        todoRepository.save(request.toEntity());
    }
}
