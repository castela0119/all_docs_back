package com.alldocs.demo.document;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanContractRepository extends MongoRepository<LoanContract, String> {
    // 사용자별 문서 조회
    List<LoanContract> findByUserId(String userId);
}


