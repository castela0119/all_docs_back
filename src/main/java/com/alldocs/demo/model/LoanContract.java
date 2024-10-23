package com.alldocs.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loanContracts")
public class LoanContract {

    @Id
    private String id;

    private String userId; // 문서를 작성한 사용자 ID

    private String lenderName; // 채권자 이름
    private String borrowerName; // 채무자 이름
    private String loanStartDate; // 대출 시작일
    private String loanEndDate; // 대출 종료일
    private String loanAmount; // 대출 금액
    private String interestRate; // 이자율

    private String lenderIdNumber; // 채권자 주민등록번호
    private String lenderAddress; // 채권자 주소
    private String lenderPhoneNumber; // 채권자 전화번호

    private String borrowerIdNumber; // 채무자 주민등록번호
    private String borrowerAddress; // 채무자 주소
    private String borrowerPhoneNumber; // 채무자 전화번호

    // 기본 생성자
    public LoanContract() {}

    // 생성자
    public LoanContract(String userId, String lenderName, String borrowerName, String loanStartDate, String loanEndDate, String loanAmount, String interestRate, String lenderIdNumber, String lenderAddress, String lenderPhoneNumber, String borrowerIdNumber, String borrowerAddress, String borrowerPhoneNumber) {
        this.userId = userId;
        this.lenderName = lenderName;
        this.borrowerName = borrowerName;
        this.loanStartDate = loanStartDate;
        this.loanEndDate = loanEndDate;
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.lenderIdNumber = lenderIdNumber;
        this.lenderAddress = lenderAddress;
        this.lenderPhoneNumber = lenderPhoneNumber;
        this.borrowerIdNumber = borrowerIdNumber;
        this.borrowerAddress = borrowerAddress;
        this.borrowerPhoneNumber = borrowerPhoneNumber;
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

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(String loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public String getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(String loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
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
}
