package com.example.spring_crud.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchNoticesWriterRequestDto {
    @NotBlank(message = "게시글 작성자를 입력해주세요.")
    private String writer;
}
