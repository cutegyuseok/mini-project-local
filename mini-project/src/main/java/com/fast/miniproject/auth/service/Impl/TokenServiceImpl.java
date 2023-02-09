package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.entity.Token;
import com.fast.miniproject.auth.repository.TokenRepository;
import com.fast.miniproject.auth.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Override
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

    @Override
    public boolean checkLogout(String token){
        return tokenRepository.existsByToken(token);
    }

}
