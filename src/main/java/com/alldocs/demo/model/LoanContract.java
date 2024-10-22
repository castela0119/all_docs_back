package com.alldocs.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "loanContracts")
public class LoanContract {

    @Id
    private String id;

    private String lenderName;
    private String borrowerName;
    private String loanStartDate;
    private String loanEndDate;
    private String loanAmount;
    private String interestRate;

    private String lenderIdNumber;
    private String lenderAddress;
    private String lenderPhoneNumber;

    private String borrowerIdNumber;
    private String borrowerAddress;
    private String borrowerPhoneNumber;

    // Constructors, Getters, Setters
    // ...
}