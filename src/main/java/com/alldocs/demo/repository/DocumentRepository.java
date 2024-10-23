package com.alldocs.demo.repository;

import com.alldocs.demo.model.BaseDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocumentRepository extends MongoRepository<BaseDocument, String> {
    List<BaseDocument> findByUserId(String userId);
}
