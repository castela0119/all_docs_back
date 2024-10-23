package com.alldocs.demo.repository;

import com.alldocs.demo.model.LoanContract;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanContractRepository extends MongoRepository<LoanContract, String> {
    List<LoanContract> findByUserId(String userId);
}
