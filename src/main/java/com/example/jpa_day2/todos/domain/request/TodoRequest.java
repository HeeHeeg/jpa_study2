package com.example.jpa_day2.todos.domain.request;

import com.example.jpa_day2.members.domain.entity.Member;
import com.example.jpa_day2.todos.domain.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class TodoRequest { //여기에 유저가 줄 데이터를 넣어주는 것.
    private String title;
    private String content;
    private Long memberId;

    //builder가 있어서 이렇게 만들어줄 수 있음.
    public Todo toEntity() {
        Member member = Member.builder()
                .id(memberId)
                .build();
//        new Todo(null, title, content, false, 0, member); // 아래와 이거 한줄이 같은 것이다.
        return Todo.builder()
                .content(content)
                .title(title)
                .member(member)
                .likeCount(0)
                .build();
    }
}
