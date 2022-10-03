package com.example.spring_crud.service.notebox;

import com.example.spring_crud.dto.notebox.*;
import com.example.spring_crud.entity.notebox.NoteBox;
import com.example.spring_crud.exception.notebox.NotFoundMessageException;
import com.example.spring_crud.exception.notebox.NotFoundNoteBoxException;
import com.example.spring_crud.exception.notebox.NotFoundUserException;
import com.example.spring_crud.repository.NoteBox.NoteBoxRepository;
import com.example.spring_crud.user.adapter.out.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteBoxService {
    private final NoteBoxRepository noteBoxRepository;
    private final UserRepository userRepository;

    // 쪽지 전체 조회
    @Transactional(readOnly = true)
    public List<GetNoteBoxResponseDto> getNoteBox(){
        List<NoteBox> noteBox = noteBoxRepository.findAll();
        if(noteBox.isEmpty()){
            throw new NotFoundNoteBoxException();
        }
        List<GetNoteBoxResponseDto> getNoteBoxResponseDtoList = new LinkedList<>();
        noteBox.forEach(note -> getNoteBoxResponseDtoList.add(new GetNoteBoxResponseDto().toDto(note)));
        return getNoteBoxResponseDtoList;
    }

    // 쪽지 쓰기
    @Transactional
    public void createNotesBox(CreateNotesBoxDto createNotesBoxDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String getUser = authentication.getName();
        userRepository.findByUsername(createNotesBoxDto.getReceiver()).orElseThrow(() -> {
            throw new NotFoundUserException();
        });
        NoteBox noteBox = new NoteBox(getUser, createNotesBoxDto.getReceiver(), createNotesBoxDto.getContent(), NoteBoxStatus.Send);
        noteBoxRepository.save(noteBox);
    }

    // 쪽지 내용 보기
    @Transactional(readOnly = true)
    public GetNotesBoxContent getNotesBoxContent(Long id){
        NoteBox noteBox = noteBoxRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundMessageException();
        });
        return new GetNotesBoxContent().toDto(noteBox);
    }

    // 쪽지 삭제
    @Transactional
    public void deleteNotesBox(Long id){
        noteBoxRepository.deleteById(id);
    }

    // 쪽지 답장 하기
    @Transactional
    public void sendNotesBox(Long id, SendNotesBoxDto sendNotesBoxDto){
        NoteBox noteBox = noteBoxRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundMessageException();
        });
        NoteBox createNote = new NoteBox(noteBox.getReceive(), noteBox.getSender(), sendNotesBoxDto.getContent(), NoteBoxStatus.Response);
        noteBoxRepository.save(createNote);
    }
}
