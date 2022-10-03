package com.example.spring_crud.controller.notebox;

import com.example.spring_crud.dto.notebox.CreateNotesBoxDto;
import com.example.spring_crud.dto.notebox.SendNotesBoxDto;
import com.example.spring_crud.response.Response;
import com.example.spring_crud.service.notebox.NoteBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NoteBoxController {
    private final NoteBoxService noteBoxService;

    // 쪽지함 전체 조회
    @GetMapping("/note/box")
    @ResponseStatus(HttpStatus.OK)
    public Response getNotesBox(){
        return Response.success(noteBoxService.getNoteBox());
    }

    // 쪽지 쓰기
    @PostMapping("/note/box")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNotesBox(@RequestBody @Valid CreateNotesBoxDto createNotesBoxDto){
        noteBoxService.createNotesBox(createNotesBoxDto);
    }

    // 쪽지 내용 보기
    @GetMapping("/note/box/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getNotesBoxContent(@PathVariable Long id){
        return Response.success(noteBoxService.getNotesBoxContent(id));
    }

    // 쪽지 삭제
    @DeleteMapping("/note/box/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteNotesBox(@PathVariable Long id){
        noteBoxService.deleteNotesBox(id);
    }

    // 쪽지 답장 하기
    @PostMapping("/note/box/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendNotesBox(@PathVariable Long id, @RequestBody @Valid SendNotesBoxDto sendNotesBoxDto){
        noteBoxService.sendNotesBox(id, sendNotesBoxDto);
    }
}
