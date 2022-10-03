package com.example.spring_crud.notice;

import com.example.spring_crud.controller.notice.NoticeController;
import com.example.spring_crud.dto.notice.*;
import com.example.spring_crud.service.notice.NoticeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class noticeControllerTest {
    @InjectMocks
    NoticeController noticeController;

    @Mock
    NoticeService noticeService;

    ObjectMapper objectMapper = new ObjectMapper();
    MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(noticeController).build();
    }

    @Test
    @DisplayName("공지사항 전체 조회")
    public void getNoticesTest() throws Exception{
        //given

        //when, then
        mockMvc.perform(get("/api/notices"))
                .andExpect(status().isOk());

        verify(noticeService).getNotices();
    }

    @Test
    @DisplayName("공지사항 제목 검색")
    public void searchNoticesTitleTest() throws Exception{
        //given
        SearchNoticesTitleRequestDto searchNoticesTitleRequestDto = new SearchNoticesTitleRequestDto("제목");

        //when, then
        mockMvc.perform(post("/api/notices/search/title")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchNoticesTitleRequestDto)))
                .andExpect(status().isOk());

        verify(noticeService).searchNoticesTitle(searchNoticesTitleRequestDto);
    }

    @Test
    @DisplayName("공지사항 생성")
    public void createBoardsTest() throws Exception{
        //given
        NoticeCreateRequestDto noticeCreateRequestDto = new NoticeCreateRequestDto("제목", "내용", "작성자");
        //when, then
        mockMvc.perform(post("/api/notices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noticeCreateRequestDto)))
                .andExpect(status().isCreated());

        verify(noticeService).createBoards(noticeCreateRequestDto);
    }

    @Test
    @DisplayName("공지사항 수정")
    public void editBoardsTest() throws Exception{
        //given
        Long id = 1L;
        NoticeEditRequestDto noticeEditRequestDto = new NoticeEditRequestDto("제목", "내용");
        //when, then
        mockMvc.perform(put("/api/notices/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noticeEditRequestDto)))
                .andExpect(status().isOk());

        verify(noticeService).editBoardsContent(id, noticeEditRequestDto);
    }

    @Test
    @DisplayName("공지사항 작성자 검색")
    public void searchNoticesWriterTest() throws Exception{
        //given
        SearchNoticesWriterRequestDto searchNoticesWriterRequestDto = new SearchNoticesWriterRequestDto("작성자");
        //when, then
        mockMvc.perform(post("/api/notices/search/writer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchNoticesWriterRequestDto)))
                .andExpect(status().isOk());

        verify(noticeService).searchNoticesWriter(searchNoticesWriterRequestDto);
    }

    @Test
    @DisplayName("공지사항 날짜별 조회")
    public void searchDateTest() throws Exception{
        //given
        LocalDate localDateStart = LocalDate.of(2021,01,01);
        LocalDate localDateEnd = LocalDate.of(2022,01,01);
        SearchNoticesDateRequestDto searchNoticesDateRequestDto = new SearchNoticesDateRequestDto(localDateStart, localDateEnd);
        //when
        noticeController.searchDate(searchNoticesDateRequestDto);

        //then
        assertThat(searchNoticesDateRequestDto.getStartDate()).isEqualTo(localDateStart);
        assertThat(searchNoticesDateRequestDto.getEndDate()).isEqualTo(localDateEnd);
    }

    @Test
    @DisplayName("공지사항 게시글 삭제")
    public void deleteBoardsTest() throws Exception{
        //given
        Long id = 1L;
        //when, then
        mockMvc.perform(delete("/api/notices/{id}",id))
                .andExpect(status().isOk());

        verify(noticeService).deleteBoards(id);
    }

    @Test
    @DisplayName("공지사항 게시글 전체 삭제")
    public void deleteAllTest() throws Exception{
        //given

        //when, then
        mockMvc.perform(delete("/api/notices"))
                .andExpect(status().isOk());

        verify(noticeService).deleteAllBoards();
    }
}
