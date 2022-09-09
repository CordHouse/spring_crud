package com.example.spring_crud.user.adapter.in.web;

import com.example.spring_crud.user.application.port.in.UserLoginUseCase;
import com.example.spring_crud.user.application.port.in.UserRegisterUseCase;
import com.example.spring_crud.user.pojo.dto.UserRegisterRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @see UserAuthController
 * 인증, 인가 도메인 영역 Controller 단위 테스트
 * // TODO: 2022-09-08 로그인 테스트
 */
@ExtendWith(MockitoExtension.class)
class UserAuthControllerTest {
    @InjectMocks
    private UserAuthController userAuthController;

    @Mock
    private UserRegisterUseCase userRegisterUseCase;

    @Mock
    private UserLoginUseCase userLoginUseCase;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(userAuthController).build();
    }

    @DisplayName("user register")
    @Test
    void userRegister() throws Exception {
        UserRegisterRequestDto userRegisterRequestDto = UserRegisterRequestDto
                .builder()
                .loginId("aaaaaaa2")
                .password("aaaaaaaaA1@")
                .build();

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRegisterRequestDto)))
                .andExpect(status().isCreated());

        verify(userRegisterUseCase).userRegister(refEq(userRegisterRequestDto));

    }

    @DisplayName("유저 로그인")
    @Test
    void testUserRegister() {
    }
}