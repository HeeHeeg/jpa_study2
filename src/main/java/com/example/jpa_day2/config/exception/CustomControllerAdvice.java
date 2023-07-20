package com.example.jpa_day2.config.exception;

import com.example.jpa_day2.config.domain.response.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomControllerAdvice {
    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400에러를 던지도록 해주는 것.
    public ErrorResponse loginFailExceptionHandler(LoginFailException e) {
        return new ErrorResponse(e.getMessage(), e.getCause());
    }

    @ExceptionHandler(DuplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse DuplicationException(DuplicationException e) {
        return new ErrorResponse(e.getMessage(), e.getCause());
    }
    @ExceptionHandler(WeakKeyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse WeakKeyExceptionHandler(WeakKeyException e) { //우리가 컨트롤하는 에러가 아니기 때문에 WeakKeyException 이렇게 해서 우리가 메세지를 뱉어주는 것.
        return new ErrorResponse("Tampered Token", e.getCause());
    }
    //시간 지나면 뜨는 에러 잡아주기.
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse WeakKeyExceptionHandler(ExpiredJwtException e) { //우리가 컨트롤하는 에러가 아니기 때문에 WeakKeyException 이렇게 해서 우리가 메세지를 뱉어주는 것.
        return new ErrorResponse("Tampered Token", e.getCause());
    }
}
