package com.alldocs.demo.model;

import com.alldocs.demo.model.LoanContract;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String nickname;
    private String email;
    private String password;

    // User가 여러 LoanContract를 가질 수 있음 (1:N 관계)
    @DBRef
    private List<LoanContract> loanContracts;

    // Constructors, Getters, Setters

    public User() {}

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<LoanContract> getLoanContracts() {
        return loanContracts;
    }

    public void setLoanContracts(List<LoanContract> loanContracts) {
        this.loanContracts = loanContracts;
    }
}