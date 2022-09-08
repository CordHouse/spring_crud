package com.example.spring_crud.user.pojo;


import lombok.*;

/**
 * @apiNote
 * 유저 도메인 영역을 책임지는 객체
 * 객체에 대한 분리는 도메인 영역의 확장에 따라 고려할 필요가 있는데 본 객체에 얼마나의 필드를 포함할 것이고 분리할 것인지가 관건(현재 Deprecated된 {@link UserLoginIdAndPassword }UserLoginIdAndPassword처럼 분리할 것인지를 의미)
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Getter
    private final UserId userId;

    @Getter
    private LoginId loginId;

    @Getter
    private LoginPassword loginPassword;




    @Value
    public static class UserId {
        private Long value;
    }

    @Value
    public static class LoginId {
        private String value;
    }

    @Value
    public static class LoginPassword {
        private String value;
    }
}
