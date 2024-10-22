package com.alldocs.demo.repository;

import com.alldocs.demo.model.LoanContract;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanContractRepository extends MongoRepository<LoanContract, String> {
}
