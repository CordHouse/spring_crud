package com.example.spring_crud.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
public class Response {
    private boolean success;
    private int code;
    private Result result;

    // 성공 시 응답
    public static <T> Response success(T data){
        return new Response(true, 0, new Success<>(data));
    }

    // 실패 시 응답
    public static Response failure(int code, String msg){
        return new Response(false, code, new Failure(msg));
    }
}
