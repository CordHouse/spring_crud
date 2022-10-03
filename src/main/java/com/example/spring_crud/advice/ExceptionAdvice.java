package com.example.spring_crud.advice;

import com.example.spring_crud.exception.notebox.NotFoundMessageException;
import com.example.spring_crud.exception.notebox.NotFoundNoteBoxException;
import com.example.spring_crud.exception.notebox.NotFoundUserException;
import com.example.spring_crud.exception.notice.NoticeBoardTitleNotFoundException;
import com.example.spring_crud.exception.notice.NoticeBoardsNotFoundException;
import com.example.spring_crud.exception.notice.NoticeNotFoundSearchDateException;
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

    // 공지사항 게시글이 존재하지 않을 경우
    @ExceptionHandler(NoticeBoardsNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response noticeBoardsNotFoundException(){
        return Response.failure(400, "게시글이 존재하지 않습니다.");
    }

    @ExceptionHandler(NoticeNotFoundSearchDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response noticeNotFoundSearchDateException(){
        return Response.failure(400, "해당일에 작성된 게시글이 존재하지 않습니다.");
    }

    @ExceptionHandler(NotFoundNoteBoxException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response notFoundNoteBoxException(){
        return Response.failure(400, "쪽지함이 비어있습니다.");
    }

    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response notFoundUserException(){
        return Response.failure(400, "해당 유저는 존재하지 않습니다.");
    }

    @ExceptionHandler(NotFoundMessageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response notFoundMessageException(){
        return Response.failure(400, "쪽지가 존재하지 않습니다.");
    }
}
