package com.alldocs.demo.service;

import com.alldocs.demo.model.User;
import com.alldocs.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 처리 (이메일 중복 체크 포함)
    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }
        return userRepository.save(user);
    }

    // 이메일과 비밀번호로 사용자 찾기 (로그인 처리)
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    // 사용자 정보 저장 또는 업데이트
    public User save(User user) {
        return userRepository.save(user);
    }

    // 이메일로 사용자 찾기
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
