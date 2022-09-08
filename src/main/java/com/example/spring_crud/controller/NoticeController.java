package com.example.spring_crud.controller;

import com.example.spring_crud.dto.NoticeCreateRequestDto;
import com.example.spring_crud.dto.SearchNoticesTitleRequestDto;
import com.example.spring_crud.response.Response;
import com.example.spring_crud.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class NoticeController {
    private final NoticeService noticeService;

    // 공지사항 전체 조회
    @GetMapping("/notice")
    @ResponseStatus(HttpStatus.OK)
    public Response getNotices(){
        return Response.success(noticeService.getNotices());
    }

    // 공지사항 제목 검색
    @PostMapping("/notice/search/title")
    @ResponseStatus(HttpStatus.OK)
    public Response searchNoticesTitle(@RequestBody @Valid SearchNoticesTitleRequestDto searchNoticesTitleRequestDto){
        return Response.success(noticeService.searchNoticesTitle(searchNoticesTitleRequestDto));
    }

    // 게시글 생성
    @PostMapping("/notice")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBoards(@RequestBody @Valid NoticeCreateRequestDto noticeCreateRequestDto){
        return Response.success(noticeService.createBoards(noticeCreateRequestDto));
    }
    // 게시글 수정
    // 작성자로 검색
    // 날짜별 조회
    // 게시글 삭제

}
