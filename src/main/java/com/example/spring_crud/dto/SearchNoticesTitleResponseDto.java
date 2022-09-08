package com.example.spring_crud.dto;

import com.example.spring_crud.entity.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchNoticesTitleResponseDto {
    private Long id;
    private String title;
    private String writer;

    public SearchNoticesTitleResponseDto toDto(Notice notice){
        return new SearchNoticesTitleResponseDto(notice.getId(), notice.getTitle(), notice.getWriter());
    }
}
