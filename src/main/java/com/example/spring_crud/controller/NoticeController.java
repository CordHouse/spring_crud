package com.example.spring_crud.controller;

import com.example.spring_crud.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class NoticeController {
    private final NoticeService noticeService;
}
