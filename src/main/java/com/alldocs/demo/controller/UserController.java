package com.alldocs.demo.controller;

import com.alldocs.demo.model.User;
import com.alldocs.demo.service.TokenService;
import com.alldocs.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService; // TokenService 주입

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
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User loginRequest) {
        try {
            // 이메일과 비밀번호로 사용자 찾기
            Optional<User> user = userService.findUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
            if (user.isPresent()) {
                // 사용자 인증 성공 시 랜덤 토큰 생성
                String token = tokenService.generateToken(); // 토큰 생성

                // 생성된 토큰을 사용자 엔티티에 저장
                User authenticatedUser = user.get();
                authenticatedUser.setToken(token); // 사용자 엔티티에 토큰 저장
                userService.save(authenticatedUser); // DB에 사용자 정보 업데이트

                Map<String, String> response = new HashMap<>();
                response.put("nickname", authenticatedUser.getNickname()); // 닉네임 전달
                response.put("token", token); // 생성된 토큰 전달

                return ResponseEntity.ok(response); // JSON 형식으로 응답
            } else {
                return ResponseEntity.status(401).body(null); // 인증 실패 시 401 응답
            }
        } catch (Exception e) {
            logger.error("로그인 중 오류 발생: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(null); // 서버 오류 처리
        }
    }
}
