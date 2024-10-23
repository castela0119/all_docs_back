package com.alldocs.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
public class BaseDocument {

    @Id
    private String id;
    private String userId; // 문서를 작성한 유저 ID
    private String documentType; // 문서 타입 (예: "LoanContract")

    // 기본 생성자
    public BaseDocument() {}

    // 생성자
    public BaseDocument(String userId, String documentType) {
        this.userId = userId;
        this.documentType = documentType;
    }

    // Getter 및 Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
