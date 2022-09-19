package com.example.spring_crud.dto.notice;

import com.example.spring_crud.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchNoticesDateResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDate date;

    public SearchNoticesDateResponseDto toDto(Notice notice){
        return new SearchNoticesDateResponseDto(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter(), notice.getDate());
    }
}
