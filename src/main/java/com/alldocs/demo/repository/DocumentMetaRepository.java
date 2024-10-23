package com.alldocs.demo.repository;

import com.alldocs.demo.model.DocumentMeta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentMetaRepository extends MongoRepository<DocumentMeta, String> {
    List<DocumentMeta> findByUserId(String userId);
}
