package com.fast.miniproject.auth.service;

public interface TokenService {

    String logout(String header);
    boolean checkLogout(String token);
}
