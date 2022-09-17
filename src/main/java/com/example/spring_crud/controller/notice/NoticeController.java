package com.example.spring_crud.controller.notice;

import com.example.spring_crud.dto.notice.*;
import com.example.spring_crud.response.Response;
import com.example.spring_crud.service.notice.NoticeService;
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
    @GetMapping("/notices")
    @ResponseStatus(HttpStatus.OK)
    public Response getNotices(){
        return Response.success(noticeService.getNotices());
    }

    // 공지사항 제목 검색
    @PostMapping("/notices/search/title")
    @ResponseStatus(HttpStatus.OK)
    public Response searchNoticesTitle(@RequestBody @Valid SearchNoticesTitleRequestDto searchNoticesTitleRequestDto){
        return Response.success(noticeService.searchNoticesTitle(searchNoticesTitleRequestDto));
    }

    // 게시글 생성
    @PostMapping("/notices")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createBoards(@RequestBody @Valid NoticeCreateRequestDto noticeCreateRequestDto){
        return Response.success(noticeService.createBoards(noticeCreateRequestDto));
    }
    // 게시글 수정 ( title, content 수정 )
    @PutMapping("/notices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response editBoards(@PathVariable Long id, @RequestBody @Valid NoticeEditRequestDto noticeEditRequestDto){
        return Response.success(noticeService.editBoardsContent(id, noticeEditRequestDto));
    }
    // 작성자로 검색
    @PostMapping("/notices/search/writer")
    @ResponseStatus(HttpStatus.OK)
    public Response searchNoticesWriter(@RequestBody @Valid SearchNoticesWriterRequestDto searchNoticesWriterRequestDto){
        return Response.success(noticeService.searchNoticesWriter(searchNoticesWriterRequestDto));
    }
    // 날짜별 조회
    @PostMapping("notices/search/date")
    @ResponseStatus(HttpStatus.OK)
    public Response searchDate(@RequestBody @Valid SearchNoticesDateRequestDto searchNoticesDateRequestDto){
        return Response.success(noticeService.searchNoticesDate(searchNoticesDateRequestDto));
    }
    // 게시글 삭제
    @DeleteMapping("/notices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteBoards(@PathVariable Long id){
        return Response.success(noticeService.deleteBoards(id));
    }
    // 게시글 전체 삭제
    @DeleteMapping("/notices")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteAllBoards(){
        return Response.success(noticeService.deleteAllBoards());
    }

}
