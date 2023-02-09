package com.fast.miniproject.auth.service;

import com.fast.miniproject.global.response.ResponseDTO;

public interface TokenService {

    ResponseDTO<?> logout(String header);
    boolean checkLogout(String token);
}
