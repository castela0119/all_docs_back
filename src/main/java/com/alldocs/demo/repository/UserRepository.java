package com.alldocs.demo.repository;

import com.alldocs.demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // 이메일로 사용자 조회 메서드 추가
    Optional<User> findByEmail(String email);
}
