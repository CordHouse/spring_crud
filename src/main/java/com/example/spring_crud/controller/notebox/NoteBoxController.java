package com.example.spring_crud.controller.notebox;

import com.example.spring_crud.repository.NoteBox.NoteBoxRepository;
import com.example.spring_crud.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class NoteBoxController {
    private NoteBoxRepository noteBoxRepository;

    @GetMapping("/note/box")
    @ResponseStatus(HttpStatus.OK)
    private Response getNotes(){
        return Response.success(noteBoxRepository.getNoteBox());
    }
}
