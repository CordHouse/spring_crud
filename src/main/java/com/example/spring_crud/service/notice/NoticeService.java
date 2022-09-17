package com.example.spring_crud.service.notice;

import com.example.spring_crud.dto.notice.*;
import com.example.spring_crud.entity.notice.Notice;
import com.example.spring_crud.exception.notice.NoticeBoardTitleNotFoundException;
import com.example.spring_crud.exception.notice.NoticeBoardsNotFoundException;
import com.example.spring_crud.exception.notice.NoticeNotFoundSearchDateException;
import com.example.spring_crud.repository.notice.NoticeRepository;
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
    public List<SearchNoticesResponseDto> searchNoticesTitle(SearchNoticesTitleRequestDto searchNoticesTitleRequestDto){
        List<Notice> notices = noticeRepository.findAllByTitleContaining(searchNoticesTitleRequestDto.getTitle()).orElseThrow();
        if(notices.isEmpty())
            throw new NoticeBoardTitleNotFoundException();
        List<SearchNoticesResponseDto> searchNoticesResponseDtoList = new LinkedList<>();
        notices.forEach(noticeTitle -> searchNoticesResponseDtoList.add(new SearchNoticesResponseDto().toDto(noticeTitle)));
        return searchNoticesResponseDtoList;
    }

    // 게시글 생성
    @Transactional
    public NoticeCreateResponseDto createBoards(NoticeCreateRequestDto noticeCreateRequestDto){
        Notice notices = new Notice(noticeCreateRequestDto.getTitle(), noticeCreateRequestDto.getContent(), noticeCreateRequestDto.getWriter());
        noticeRepository.save(notices);
        return new NoticeCreateResponseDto().toDto(notices);
    }

    // 게시글 수정 ( title, content )
    @Transactional
    public NoticeEditResponseDto editBoardsContent(Long id, NoticeEditRequestDto noticeEditRequestDto){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            throw new NoticeBoardsNotFoundException();
        });
        // 제목 수정
        if(!noticeEditRequestDto.getTitle().equals("")){
            notice.setTitle(noticeEditRequestDto.getTitle());
        }
        // 내용 수정
        if(!noticeEditRequestDto.getContent().equals("")) {
            notice.setContent(noticeEditRequestDto.getContent());
        }
        return new NoticeEditResponseDto().toDto(notice);
    }

    // 작성자 조회
    @Transactional
    public List<SearchNoticesResponseDto> searchNoticesWriter(SearchNoticesWriterRequestDto searchNoticesWriterRequestDto){
        List<Notice> notices = noticeRepository.findAllByWriterContaining(searchNoticesWriterRequestDto.getWriter()).orElseThrow();
        if(notices.isEmpty())
            throw new NoticeBoardTitleNotFoundException();
        List<SearchNoticesResponseDto> searchNoticesResponseDtoList = new LinkedList<>();
        notices.forEach(noticeWriter -> searchNoticesResponseDtoList.add(new SearchNoticesResponseDto().toDto(noticeWriter)));
        return searchNoticesResponseDtoList;
    }

    // 날짜별 조회
    @Transactional
    public List<SearchNoticesDateResponseDto> searchNoticesDate(SearchNoticesDateRequestDto searchNoticesDateRequestDto){
        List<Notice> notice = noticeRepository.findAllByDateBetween(searchNoticesDateRequestDto.getStartDate(), searchNoticesDateRequestDto.getEndDate()).orElseThrow();
        if(notice.isEmpty()){
            throw new NoticeNotFoundSearchDateException();
        }
        List<SearchNoticesDateResponseDto> searchNoticesDateResponseDtoList = new LinkedList<>();
        notice.forEach(date -> searchNoticesDateResponseDtoList.add(new SearchNoticesDateResponseDto().toDto(date)));
        return searchNoticesDateResponseDtoList;
    }

    // 게시글 삭제
    @Transactional
    public String deleteBoards(Long id){
        Notice notice = noticeRepository.findById(id).orElseThrow(() -> {
            throw new NoticeBoardsNotFoundException();
        });
        noticeRepository.deleteById(notice.getId());
        return id + "번 게시글이 삭제되었습니다.";
    }
    // 게시글 전체 삭제
    @Transactional
    public String deleteAllBoards(){
        noticeRepository.deleteAll();
        return "게시글이 전부 삭제되었습니다.";
    }
}
