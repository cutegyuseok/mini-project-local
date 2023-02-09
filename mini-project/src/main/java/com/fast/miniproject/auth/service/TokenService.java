package com.fast.miniproject.auth.service;

import com.fast.miniproject.auth.entity.Token;
import com.fast.miniproject.auth.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public String logout(String header){
        if (checkLogout(header)){
            return "already";
        }else {
            try {
                tokenRepository.save(Token.builder().token(header).build());
                return "success";
            } catch (Exception e) {
                return "failed";
            }
        }
    }

    public boolean checkLogout(String token){
        return tokenRepository.existsByToken(token);
    }

}
