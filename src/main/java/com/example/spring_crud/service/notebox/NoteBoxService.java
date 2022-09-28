package com.example.spring_crud.service.notebox;

import com.example.spring_crud.entity.notebox.NoteBox;
import com.example.spring_crud.repository.NoteBox.NoteBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteBoxService {
    private NoteBoxRepository noteBoxRepository;

    @Transactional
    public GetNoteBoxResponseDto getNoteBox(GetNoteBoxRequestDto getNoteBoxRequestDto){
        NoteBox noteBox = noteBoxRepository.findAllBySend(getNoteBoxRequestDto.getSend()).orElseThrow();

    }
}
