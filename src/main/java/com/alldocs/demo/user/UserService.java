package com.alldocs.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 사용자 저장
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 카카오 ID로 사용자 조회
    public User findByKakaoId(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId);
    }
}

