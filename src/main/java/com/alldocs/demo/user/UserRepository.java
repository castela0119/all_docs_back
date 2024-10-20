package com.alldocs.demo.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    // 카카오 ID를 통해 사용자 찾기
    User findByKakaoId(String kakaoId);
}
