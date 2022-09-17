package com.example.spring_crud.entity.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    public Notice(String title, String content, String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @PrePersist
    public void createDate(){
        this.date = LocalDate.now();
    }
}
