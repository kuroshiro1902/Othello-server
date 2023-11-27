package com.example.Othello2.authserver.service;

import com.example.Othello2.authserver.dto.user.UserRequest;
import com.example.Othello2.authserver.entity.UserEntity;
import com.example.Othello2.authserver.mapper.UserMapper;
import com.example.Othello2.authserver.repository.UserRepository;
import com.example.Othello2.authserver.token.TokenHandler;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public String signUp(UserRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new RuntimeException("Username already exists!!!");
        }
        signUpRequest.setPassword(BCrypt.hashpw(signUpRequest.getPassword(), BCrypt.gensalt()));
        UserEntity userEntity = UserMapper.getEntityFromRequest(signUpRequest);
        userRepository.save(userEntity);
        // tạo 4 độ khó
        return TokenHandler.generateToken(userEntity);
    }

    public String logIn(UserRequest logInRequest){
        UserEntity userEntity = userRepository.findByUsername(logInRequest.getUsername());
        String currentHashedPassword = userEntity.getPassword();
        if (BCrypt.checkpw(logInRequest.getPassword(), currentHashedPassword)){
            return TokenHandler.generateToken(userEntity);
        }
        throw new RuntimeException("Incorrect password!!!");
    }

}