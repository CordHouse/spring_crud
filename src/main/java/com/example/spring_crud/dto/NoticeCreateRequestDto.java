package com.example.spring_crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateRequestDto {
    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "게시글 작성자를 입력해주세요.")
    private String writer;
}
