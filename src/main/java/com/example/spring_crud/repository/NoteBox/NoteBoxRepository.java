package com.example.spring_crud.repository.NoteBox;

import com.example.spring_crud.entity.notebox.NoteBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteBoxRepository extends JpaRepository<NoteBox, Long> {
    Optional<List<NoteBox>> findAllBySend(String send);
}
