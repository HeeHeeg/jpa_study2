package com.example.jpa_day2.members.domain.entity;

import com.example.jpa_day2.todos.domain.entity.Todo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "members") //테이블은 복수로 저장하겠다고 지정.
@Getter @AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY를 해야 오토 인크리먼트로 생성됨.
    private Long id;

    //  @Column(unique = true, nullable = false) 이런거 주는게 좋긴 하지만 의미가 없다.
    //  결국 확인을 하려면 디비를 봐야함. 디비 가기 전에 자바에서 걸러주는게 가장 좋다.테이블 생성할 떄 좋게 해준다 정도.
    @Column(unique = true, nullable = false)
    private String email;

    private String password, name;// 이렇게 해도 되나? 테이블 생성이 되면 되는 것.

    private Integer age;

    //+멤버는 많은 todo를 가지고 있음. 나는(member)한명. 게시글은 많을 수 있음. 필드값 적어줘야 함.
    @OneToMany(mappedBy = "member")
    private List<Todo> todos;

}
