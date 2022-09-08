package com.example.spring_crud.advice;

import com.example.spring_crud.exception.NoticeBoardTitleNotFoundException;
import com.example.spring_crud.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    // Valid 에러처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException e){
        return Response.failure(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    // 공지사항 제목으로 검색 에러 처리
    @ExceptionHandler(NoticeBoardTitleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response noticeBoardTitleNotFoundException(){
        return Response.failure(400, "일치하는 공지사항이 존재하지 않습니다.");
    }
}
