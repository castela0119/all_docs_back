package com.alldocs.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // 사용자 저장 (카카오 로그인 후 사용자 정보 저장)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // 카카오 ID로 사용자 조회
        User existingUser = userService.findByKakaoId(user.getKakaoId());

        // 사용자가 존재하지 않으면 저장
        if (existingUser == null) {
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }

        // 존재하는 사용자를 반환
        return ResponseEntity.ok(existingUser);
    }

    // 카카오 ID로 사용자 조회
    @GetMapping("/{kakaoId}")
    public ResponseEntity<User> getUserByKakaoId(@PathVariable String kakaoId) {
        User user = userService.findByKakaoId(kakaoId);

        // 사용자가 존재하지 않으면 404 Not Found 반환
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}


