package com.example.spring_crud.dto.notice;

import com.example.spring_crud.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchNoticesResponseDto {
    private Long id;
    private String title;
    private String writer;

    public SearchNoticesResponseDto toDto(Notice notice){
        return new SearchNoticesResponseDto(notice.getId(), notice.getTitle(), notice.getWriter());
    }
}
