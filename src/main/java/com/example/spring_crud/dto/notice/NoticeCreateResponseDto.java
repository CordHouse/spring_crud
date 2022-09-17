package com.example.spring_crud.dto.notice;

import com.example.spring_crud.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate time;

    public NoticeCreateResponseDto toDto(Notice notice){
        return new NoticeCreateResponseDto(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getDate());
    }
}
