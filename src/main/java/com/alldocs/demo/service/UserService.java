package com.alldocs.demo.service;

import com.alldocs.demo.model.User;
import com.alldocs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Logger 추가
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    // 회원가입 처리 (이메일 중복 체크 추가)
    public User registerUser(User user) {
        try {
            // 이메일 중복 여부 확인
            Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                logger.warn("회원가입 실패: 이미 존재하는 이메일 - {}", user.getEmail());
                throw new RuntimeException("이미 존재하는 이메일입니다.");
            }

            // 이메일이 중복되지 않으면 사용자 저장
            User savedUser = userRepository.save(user);
            logger.info("회원가입 성공: {}", user.getEmail());
            return savedUser;

        } catch (Exception e) {
            logger.error("회원가입 처리 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("회원가입 실패");
        }
    }

    // 사용자 인증 로직 추가
    public boolean authenticateUser(String email, String password) {
        try {
            // 이메일로 사용자 조회
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent() && user.get().getPassword().equals(password)) {
                // 비밀번호가 일치하는 경우 true 반환
                logger.info("사용자 인증 성공: {}", email);
                return true;
            } else {
                // 사용자 없거나 비밀번호 불일치
                logger.warn("사용자 인증 실패: {}", email);
                return false;
            }
        } catch (Exception e) {
            logger.error("사용자 인증 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("로그인 실패");
        }
    }

    // 기타 메서드...
}