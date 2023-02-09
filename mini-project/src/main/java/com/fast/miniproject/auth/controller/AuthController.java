package com.fast.miniproject.auth.controller;


import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;
import com.fast.miniproject.auth.service.Impl.TokenServiceImpl;
import com.fast.miniproject.auth.service.Impl.UserServiceImpl;
import com.fast.miniproject.global.response.ResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    private final TokenServiceImpl tokenServiceImpl;

    @PostMapping("/register")
    public ResponseDTO<?> signUp(SignupReqDTO signupReqDTO) {
        return userServiceImpl.signup(signupReqDTO);
    }

    @PostMapping("/login")
    public ResponseDTO<?> signIn(LoginReqDTO loginReqDTO) {
        return userServiceImpl.login(loginReqDTO);
    }

    @PostMapping("/logout")
    public ResponseDTO<?> logout(@RequestHeader(name="Authorization") String header){
        return tokenServiceImpl.logout(header);
    }
}
