package com.alldocs.demo.controller;

import com.alldocs.demo.model.User;
import com.alldocs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Logger 추가
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            logger.info("회원가입 성공: {}", user.getEmail());
            return ResponseEntity.ok("회원가입 성공");
        } catch (RuntimeException e) {
            logger.warn("회원가입 실패: {}", e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage()); // 400 Bad Request
        } catch (Exception e) {
            logger.error("회원가입 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("서버 오류");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginRequest) {
        try {
            boolean isAuthenticated = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (isAuthenticated) {
                logger.info("로그인 성공: {}", loginRequest.getEmail());
                return ResponseEntity.ok("로그인 성공");
            } else {
                logger.warn("로그인 실패: 이메일 또는 비밀번호가 일치하지 않음 - {}", loginRequest.getEmail());
                return ResponseEntity.status(401).body("로그인 실패");
            }
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("서버 오류");
        }
    }
}
