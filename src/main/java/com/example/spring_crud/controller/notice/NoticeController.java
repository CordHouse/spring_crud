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
@RequestMapping("/api")
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
    public void searchNoticesTitle(@RequestBody @Valid SearchNoticesTitleRequestDto searchNoticesTitleRequestDto){
        noticeService.searchNoticesTitle(searchNoticesTitleRequestDto);
    }

    // 게시글 생성
    @PostMapping("/notices")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoards(@RequestBody @Valid NoticeCreateRequestDto noticeCreateRequestDto){
        noticeService.createBoards(noticeCreateRequestDto);
    }
    // 게시글 수정 ( title, content 수정 )
    @PutMapping("/notices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void editBoards(@PathVariable Long id, @RequestBody @Valid NoticeEditRequestDto noticeEditRequestDto){
        noticeService.editBoardsContent(id, noticeEditRequestDto);
    }
    // 작성자로 검색
    @PostMapping("/notices/search/writer")
    @ResponseStatus(HttpStatus.OK)
    public void searchNoticesWriter(@RequestBody @Valid SearchNoticesWriterRequestDto searchNoticesWriterRequestDto){
        noticeService.searchNoticesWriter(searchNoticesWriterRequestDto);
    }
    // 날짜별 조회
    @PostMapping("notices/search/date")
    @ResponseStatus(HttpStatus.OK)
    public void searchDate(@RequestBody @Valid SearchNoticesDateRequestDto searchNoticesDateRequestDto){
        noticeService.searchNoticesDate(searchNoticesDateRequestDto);
    }
    // 게시글 삭제
    @DeleteMapping("/notices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBoards(@PathVariable Long id){
        noticeService.deleteBoards(id);
    }
    // 게시글 전체 삭제
    @DeleteMapping("/notices")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllBoards(){
        noticeService.deleteAllBoards();
    }

}
