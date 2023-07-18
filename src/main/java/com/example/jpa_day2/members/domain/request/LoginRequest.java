package com.example.jpa_day2.members.domain.request;

public record LoginRequest(String email, String password) { //이건 굳이 엔티티로 만들필요 없어서 안만들어줄거다.
}
