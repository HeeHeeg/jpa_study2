package com.example.jpa_day2.todos.domain.entity;

import com.example.jpa_day2.members.domain.entity.Members;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todos")
@AllArgsConstructor @NoArgsConstructor
@Builder @Getter
public class Todo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private boolean isDone; // null이 들어오면 에러가뜰 수 있으니까 이렇게 해둔다.?
    private Integer likeCount;

    //+주가 되는 테이블을 설정해줘야함. todo는 멤버1명을 가지고 있음. 나(todo)는 많다.
    @ManyToOne
    private Members member;
}
