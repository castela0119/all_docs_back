package com.alldocs.demo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "loanContracts")
public class LoanContract {

    @Id
    private String id;  // MongoDB에서 사용되는 기본 키
    private String borrowerName;
    private String lenderName;
    private LocalDate loanStartDate;
    private LocalDate loanEndDate;
    private Double loanAmount;
    private Double interestRate;
    private String lenderIdNumber;
    private String lenderAddress;
    private String lenderPhoneNumber;
    private String borrowerIdNumber;
    private String borrowerAddress;
    private String borrowerPhoneNumber;

    // 해당 문서가 속한 사용자 ID (참조)
    private String userId;

    private boolean saved; // 저장 여부 추가
    private String title; // 문서 제목 추가

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 생성자, 게터/세터
    public LoanContract() {}

    public LoanContract(String borrowerName, String lenderName, LocalDate loanStartDate, LocalDate loanEndDate, Double loanAmount, Double interestRate) {
        this.borrowerName = borrowerName;
        this.lenderName = lenderName;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public String getLenderIdNumber() {
        return lenderIdNumber;
    }

    public void setLenderIdNumber(String lenderIdNumber) {
        this.lenderIdNumber = lenderIdNumber;
    }

    public String getLenderAddress() {
        return lenderAddress;
    }

    public void setLenderAddress(String lenderAddress) {
        this.lenderAddress = lenderAddress;
    }

    public String getLenderPhoneNumber() {
        return lenderPhoneNumber;
    }

    public void setLenderPhoneNumber(String lenderPhoneNumber) {
        this.lenderPhoneNumber = lenderPhoneNumber;
    }

    public String getBorrowerIdNumber() {
        return borrowerIdNumber;
    }

    public void setBorrowerIdNumber(String borrowerIdNumber) {
        this.borrowerIdNumber = borrowerIdNumber;
    }

    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    public void setBorrowerAddress(String borrowerAddress) {
        this.borrowerAddress = borrowerAddress;
    }

    public String getBorrowerPhoneNumber() {
        return borrowerPhoneNumber;
    }

    public void setBorrowerPhoneNumber(String borrowerPhoneNumber) {
        this.borrowerPhoneNumber = borrowerPhoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}


