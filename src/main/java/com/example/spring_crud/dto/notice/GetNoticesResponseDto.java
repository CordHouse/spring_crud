package com.example.spring_crud.dto.notice;

import com.example.spring_crud.entity.notice.Notice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetNoticesResponseDto {
    private Long id;
    private String title;
    private String content;
    private String writer;

    public GetNoticesResponseDto toDto(Notice notice){
        return new GetNoticesResponseDto(notice.getId(), notice.getTitle(), notice.getContent(), notice.getWriter());
    }
}
