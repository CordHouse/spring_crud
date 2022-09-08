package com.example.spring_crud.service;

import com.example.spring_crud.dto.*;
import com.example.spring_crud.entity.Notice;
import com.example.spring_crud.exception.NoticeBoardTitleNotFoundException;
import com.example.spring_crud.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    // 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<GetNoticesResponseDto> getNotices(){
        List<Notice> notices = noticeRepository.findAll();
        if(notices.isEmpty())
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        List<GetNoticesResponseDto> getNoticesResponseDtoList = new LinkedList<>();
        notices.forEach(notice -> getNoticesResponseDtoList.add(new GetNoticesResponseDto().toDto(notice)));
        return getNoticesResponseDtoList;
    }

    // 게시글 검색 조회
    @Transactional
    public List<SearchNoticesTitleResponseDto> searchNoticesTitle(SearchNoticesTitleRequestDto searchNoticesTitleRequestDto){
        List<Notice> notices = noticeRepository.findAllByTitleContaining(searchNoticesTitleRequestDto.getTitle()).orElseThrow();
        if(notices.isEmpty())
            throw new NoticeBoardTitleNotFoundException();
        List<SearchNoticesTitleResponseDto> searchNoticesTitleResponseDtoList = new LinkedList<>();
        notices.forEach(noticeTitle -> searchNoticesTitleResponseDtoList.add(new SearchNoticesTitleResponseDto().toDto(noticeTitle)));
        return searchNoticesTitleResponseDtoList;
    }

    // 게시글 생성
    @Transactional
    public NoticeCreateResponseDto createBoards(NoticeCreateRequestDto noticeCreateRequestDto){
        Notice notices = new Notice(noticeCreateRequestDto.getTitle(), noticeCreateRequestDto.getContent(), noticeCreateRequestDto.getWriter());
        noticeRepository.save(notices);
        return new NoticeCreateResponseDto().toDto(notices);
    }
}
