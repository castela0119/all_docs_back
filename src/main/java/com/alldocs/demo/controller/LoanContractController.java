package com.alldocs.demo.controller;

import com.alldocs.demo.model.LoanContract;
import com.alldocs.demo.service.LoanContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loanContracts")
public class LoanContractController {

    @Autowired
    private LoanContractService loanContractService;

    @PostMapping
    public ResponseEntity<LoanContract> createLoanContract(@RequestBody LoanContract loanContract) {
        LoanContract createdLoanContract = loanContractService.createLoanContract(loanContract);
        return ResponseEntity.ok(createdLoanContract);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanContract> getLoanContractById(@PathVariable String id) {
        LoanContract loanContract = loanContractService.getLoanContractById(id);
        return ResponseEntity.ok(loanContract);
    }

    @GetMapping
    public ResponseEntity<List<LoanContract>> getAllLoanContracts() {
        List<LoanContract> loanContracts = loanContractService.getAllLoanContracts();
        return ResponseEntity.ok(loanContracts);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanContract(@PathVariable String id) {
        loanContractService.deleteLoanContract(id);
        return ResponseEntity.noContent().build();
    }
}
