package com.example.jpa_day2.config.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//이렇게 하면 우리가 애노테이션을 달 수 있다. 이 어노테이션이 붙은 곳은 토큰이 무조건 필요하다고 만들어주는 것.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenRequired {

}
