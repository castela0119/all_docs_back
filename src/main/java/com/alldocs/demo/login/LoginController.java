package com.alldocs.demo.login;

import com.alldocs.demo.user.User;
import com.alldocs.demo.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    private final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String CLIENT_ID = "adb6f77da0f678a2516b555d621e8aa0"; // REST API 키

    @PostMapping("/login")
    public ResponseEntity<User> loginWithKakao(@RequestBody Map<String, String> request) {

        System.out.println("login api 실행");
        
        String accessToken = request.get("accessToken");
        try {
            // 사용자 정보 요청
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> userInfoResponse = restTemplate.exchange(KAKAO_USER_INFO_URL, HttpMethod.GET, entity, Map.class);
            Map<String, Object> userInfo = userInfoResponse.getBody();

            // 사용자 정보 추출
            String kakaoId = userInfo.get("id").toString();
            Map<String, Object> properties = (Map<String, Object>) userInfo.get("properties");
            String name = (String) properties.get("nickname");
            Map<String, Object> kakaoAccount = (Map<String, Object>) userInfo.get("kakao_account");
            String email = (String) kakaoAccount.get("email");

            // 사용자 저장 또는 업데이트 로직
            User user = userService.findByKakaoId(kakaoId);
            if (user == null) {
                user = new User(kakaoId, name, email);
            } else {
                user.setName(name);
                user.setEmail(email);
            }
            userService.saveUser(user);

            System.out.println("사용자 정보 저장 :: " +  user);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        // 세션이나 JWT 토큰 등을 처리하여 로그아웃 처리
        // 필요에 따라 로직 추가
        return ResponseEntity.ok().build();
    }
}

