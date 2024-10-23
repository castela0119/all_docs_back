package com.alldocs.demo.service;

import com.alldocs.demo.model.LoanContract;
import com.alldocs.demo.repository.LoanContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanContractService {

    @Autowired
    private LoanContractRepository loanContractRepository;

    // 대출 계약서 생성 및 저장
    public LoanContract createLoanContract(LoanContract loanContract) {
        return loanContractRepository.save(loanContract); // MongoDB에 저장
    }

    // ID로 대출 계약서 조회
    public LoanContract getLoanContractById(String id) {
        return loanContractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 계약서를 찾을 수 없습니다."));
    }

    // 모든 대출 계약서 조회
    public List<LoanContract> getAllLoanContracts() {
        return loanContractRepository.findAll();
    }

    // 대출 계약서 삭제
    public void deleteLoanContract(String id) {
        LoanContract contract = getLoanContractById(id); // 존재 여부 확인
        loanContractRepository.delete(contract);
    }
}