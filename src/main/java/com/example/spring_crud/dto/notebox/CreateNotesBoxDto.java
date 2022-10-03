package com.example.spring_crud.dto.notebox;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotesBoxDto {
    @NotBlank(message = "받을 사람을 입력해주세요.")
    private String receiver;
    @NotBlank(message = "보낼 내용을 입력해주세요.")
    private String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;
}
