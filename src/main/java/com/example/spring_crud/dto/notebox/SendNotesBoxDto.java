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
public class SendNotesBoxDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;
}
