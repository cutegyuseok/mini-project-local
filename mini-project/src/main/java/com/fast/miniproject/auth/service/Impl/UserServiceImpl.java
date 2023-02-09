package com.fast.miniproject.auth.service.Impl;

import com.fast.miniproject.auth.dto.LoginReqDTO;
import com.fast.miniproject.auth.dto.SignupReqDTO;
import com.fast.miniproject.auth.entity.User;
import com.fast.miniproject.auth.jwt.JwtProvider;
import com.fast.miniproject.auth.repository.UserRepository;
import com.fast.miniproject.auth.service.UserService;
import com.fast.miniproject.global.response.ErrorResponseDTO;
import com.fast.miniproject.global.response.ResponseDTO;
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
    public ResponseDTO<?> signup(SignupReqDTO signupReqDTO){
        if(userRepository.existsByEmail(signupReqDTO.getEmail())){
            return new ResponseDTO<>(new ErrorResponseDTO(500,"이미 존재하는 회원입니다."));
        }else {
            userRepository.save(signupReqDTO.toEntity());
            return new ResponseDTO<>(ResponseDTO.empty());
        }
    }

    @Override
    public ResponseDTO<?> login(LoginReqDTO loginReqDTO){
        Optional<User> loggedIn=userRepository.findByEmailAndPassword(loginReqDTO.getEmail(),loginReqDTO.getPassword());
        try{
            User user=loggedIn.get();
            return new ResponseDTO<>(jwtProvider.token(user));
        }catch(NoSuchElementException e){
            return new ResponseDTO<>(new ErrorResponseDTO(500,"로그인에 실패하였습니다."));
        }
    }

}
