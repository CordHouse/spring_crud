package com.example.spring_crud.repository.NoteBox;

import com.example.spring_crud.entity.notebox.NoteBox;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteBoxRepository extends JpaRepository<NoteBox, Long> {
}
