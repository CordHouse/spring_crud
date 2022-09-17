package com.example.spring_crud.repository.notice;

import com.example.spring_crud.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<List<Notice>> findAllByTitleContaining(String title);
    Optional<List<Notice>> findAllByWriterContaining(String writer);
    Optional<List<Notice>> findAllByDateBetween(LocalDate start, LocalDate end);
}
