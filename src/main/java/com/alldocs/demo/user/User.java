package com.alldocs.demo.user;

import com.alldocs.demo.document.LoanContract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;  // MongoDB에서 사용되는 기본 키 (카카오 ID로 사용할 수도 있음)
    private String kakaoId;
    private String name;
    private String email;

    // 사용자가 소유한 문서 (LoanContract) 리스트
    private List<LoanContract> loanContracts;

    // 생성자, 게터/세터
    public User() {}

    public User(String kakaoId, String name, String email) {
        this.kakaoId = kakaoId;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKakaoId() {
        return kakaoId;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LoanContract> getLoanContracts() {
        return loanContracts;
    }

    public void setLoanContracts(List<LoanContract> loanContracts) {
        this.loanContracts = loanContracts;
    }
}
