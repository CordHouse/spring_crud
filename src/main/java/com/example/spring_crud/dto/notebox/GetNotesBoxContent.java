package com.example.spring_crud.dto.notebox;

import com.example.spring_crud.entity.notebox.NoteBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNotesBoxContent {
    private String sender;
    private String content;
    private LocalDate date;

    public GetNotesBoxContent toDto(NoteBox noteBox){
        return new GetNotesBoxContent(noteBox.getSender(), noteBox.getNote(), noteBox.getDate());
    }
}
