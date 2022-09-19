package com.example.spring_crud.user.application.service;

import com.example.spring_crud.user.application.port.in.UserContext;
import com.example.spring_crud.user.application.port.out.CustomUserDetailsManager;
import com.example.spring_crud.user.application.port.out.LoadAccountPort;
import com.example.spring_crud.user.pojo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @apiNote SecurityContextHolder를 이용해 현재 로그인 된 식별자를 조회, 변경한다.
 */
@RequiredArgsConstructor
@Service
public class SpringSecurityUserContext implements UserContext {
    //UserContext, UserDetailService, AuthenticationProvider 중 택1

    final private LoadAccountPort loadAccountPort;

    final private CustomUserDetailsManager userDetailService;

    @Override
    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();;
        if(authentication == null) return null;

//        String username = SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName();
//        return loadAccountPort.loadUser(new User.LoginId(username));
        return (User) authentication.getPrincipal();
    }

    @Override
    public void setCurrentUser(User user){
//        UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserId().toString());
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getLoginPassword(), userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Collection authorities =
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
