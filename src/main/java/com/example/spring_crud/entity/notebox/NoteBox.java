package com.example.spring_crud.entity.notebox;

import com.example.spring_crud.dto.notebox.CreateNotesBoxDto;
import com.example.spring_crud.dto.notebox.NoteBoxStatus;
import com.example.spring_crud.user.adapter.out.persistence.jpaentity.UserJpaEntity;
import com.example.spring_crud.user.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NoteBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false)
    private String receive;

    @Column(nullable = false)
    private String note;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoteBoxStatus position;

    @PrePersist
    public void createDate(){
        this.date = LocalDate.now();
    }

    public NoteBox(String sender, String receive, String note, NoteBoxStatus position){
        this.sender = sender;
        this.receive = receive;
        this.note = note;
        this.position = position;
    }
}
