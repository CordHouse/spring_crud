package com.example.spring_crud.repository;

import com.example.spring_crud.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<List<Notice>> findAllByTitleContaining(String title);
}
