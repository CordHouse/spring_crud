package com.example.spring_crud.dto.notebox;

import com.example.spring_crud.entity.notebox.NoteBox;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNoteBoxResponseDto {
    private Long id;
    private String sender;
    private String receiver;
    private String note;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private NoteBoxStatus position;

    public GetNoteBoxResponseDto toDto(NoteBox noteBox){
        return new GetNoteBoxResponseDto(noteBox.getId(), noteBox.getSender(), noteBox.getReceive(), noteBox.getNote(), noteBox.getDate(), noteBox.getPosition());
    }
}
