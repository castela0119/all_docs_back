package com.alldocs.demo.service;

import com.alldocs.demo.model.User;
import com.alldocs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class TokenService {

    @Autowired
    private UserRepository userRepository;

    // 간단한 랜덤 토큰 생성
    public String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] tokenBytes = new byte[24];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    // 토큰을 기반으로 사용자 조회
    public User getUserByToken(String token) {
        return userRepository.findByToken(token); // 토큰을 저장한 사용자를 조회
    }
}
