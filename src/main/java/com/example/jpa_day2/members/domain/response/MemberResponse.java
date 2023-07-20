package com.example.jpa_day2.members.domain.response;

import com.example.jpa_day2.config.domain.dto.MemberDto;
import com.example.jpa_day2.config.domain.dto.TodoDto;
import com.example.jpa_day2.members.domain.entity.Members;
import com.example.jpa_day2.todos.domain.entity.Todo;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.List;
@Getter
public class MemberResponse extends MemberDto {
    private List<TodoDto> todos;  // 순환참조를 뺀 dto를 쓰려고 하는 것.

    public MemberResponse(Members member) {
        super(member);
        todos = member.getTodos()
                .stream()
                .map(TodoDto::new)
                .toList();
    }




}
