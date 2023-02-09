package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.jwt.JwtProvider;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public String signup(SignupReqDTO signupReqDTO){
        if(userRepository.findByEmail(signupReqDTO.getEmail())){
            return "failed";
        }else {
            userRepository.save(signupReqDTO.toEntity());
            return "success";
        }
    }

    @Override
    public String login(LoginReqDTO loginReqDTO){
        Optional<User> loggedIn=userRepository.findByEmailAndPassword(loginReqDTO.getEmail(),loginReqDTO.getPassword());
        try{
            User user=loggedIn.get();
            return jwtProvider.token(user);
        }catch(NoSuchElementException e){
            return "failed";
        }
    }

}
