package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;

public interface UserService {

    String signup(SignupReqDTO signupReqDTO);
    String login(LoginReqDTO loginReqDTO);
}
