package com.alldocs.demo.service;

import com.alldocs.demo.model.LoanContract;
import com.alldocs.demo.repository.LoanContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanContractService {

    @Autowired
    private LoanContractRepository loanContractRepository;

    public LoanContract createLoanContract(LoanContract loanContract) {
        return loanContractRepository.save(loanContract);
    }

    public LoanContract getLoanContractById(String id) {
        Optional<LoanContract> loanContract = loanContractRepository.findById(id);
        return loanContract.orElseThrow(() -> new RuntimeException("LoanContract not found"));
    }

    public List<LoanContract> getAllLoanContracts() {
        return loanContractRepository.findAll();
    }

    public void deleteLoanContract(String id) {
        loanContractRepository.deleteById(id);
    }
}

