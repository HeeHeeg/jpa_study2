package com.example.jpa_day2.todos.domain.response;

import com.example.jpa_day2.config.domain.dto.MemberDto;
import com.example.jpa_day2.config.domain.dto.TodoDto;
import com.example.jpa_day2.todos.domain.entity.Todo;
import lombok.Getter;

import java.util.List;
@Getter
public class TodoResponse extends TodoDto {
    private MemberDto member;
    public TodoResponse(Todo todo) {
        super(todo);
        member = new MemberDto(todo.getMember());
    }



   /* private Long id;
    private String title;
    private String content;
    private boolean isDone;
    private Integer likeCount;
    //    private MemberDto memberDto;
    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.isDone = todo.isDone();
        this.likeCount = todo.getLikeCount();
    }*/

}
