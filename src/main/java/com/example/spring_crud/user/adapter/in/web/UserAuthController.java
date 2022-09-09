package com.example.spring_crud.user.adapter.in.web;

import com.example.spring_crud.response.Response;
import com.example.spring_crud.user.application.port.in.UserLoginUseCase;
import com.example.spring_crud.user.application.port.in.UserRegisterUseCase;
import com.example.spring_crud.user.pojo.dto.UserLoginRequestDto;
import com.example.spring_crud.user.pojo.dto.UserRegisterRequestDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @apiNote
 * 유저 인증, 인가 API
 */
@RequiredArgsConstructor
@RequestMapping("/api/")
@RestController
public class UserAuthController {
    final private UserLoginUseCase userLoginUseCase;
    final private UserRegisterUseCase userRegisterUseCase;

    @ApiOperation(value = "유저 회원가입")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "User Created")
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("register")
    public Response userRegister(@ApiParam(required = true, name = "회원가입 정보") @RequestBody @Valid UserRegisterRequestDto userRegisterRequestDto){
        System.out.println("ddd");
        userRegisterUseCase.userRegister(userRegisterRequestDto);
        System.out.println("ddd");
        return Response.success(HttpStatus.CREATED);
    }

    @ApiOperation(value = "유저 로그인")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Login OK")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("login")
    public Response userRegister(@ApiParam(required = true, name = "로그인 정보") @RequestBody @Valid UserLoginRequestDto userLoginRequestDto){
        userLoginUseCase.userLogin(userLoginRequestDto);
        return Response.success(null);
    }
}
