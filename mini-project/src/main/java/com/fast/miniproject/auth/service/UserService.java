package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;
import com.fast.miniproject.global.response.ResponseDTO;

public interface UserService {

    ResponseDTO<?> signup(SignupReqDTO signupReqDTO);
    ResponseDTO<?> login(LoginReqDTO loginReqDTO);
}
