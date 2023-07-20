package com.example.jpa_day2.todos.service;

import com.example.jpa_day2.config.service.MemberLoginService;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.todos.domain.request.TodoRequest;
import com.example.jpa_day2.todos.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final MemberLoginService memberLoginService;

    public void insert(TodoRequest request) {
        Members byMember = memberLoginService.findByMember(request.getMemberId()); //로그인 되있는지 체크하는 것.
        todoRepository.save(request.toEntity());
    }

}
