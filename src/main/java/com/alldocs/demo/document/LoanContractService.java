package com.alldocs.demo.document;

import com.alldocs.demo.exception.ResourceNotFoundException;
import com.alldocs.demo.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanContractService {

    @Autowired
    private LoanContractRepository loanContractRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoanContractService.class);

    public LoanContract saveLoanContract(LoanContract contract, String userId) {
        contract.setSaved(true); // 문서가 저장되었음을 표시
        // 문서 제목 자동 설정
        String title = contract.getLenderName() + "-" + contract.getBorrowerName() + " 차용증";
        contract.setTitle(title); // 제목 설정

        // 사용자 ID 설정
        contract.setUserId(userId); // 로그인된 사용자 ID 설정

        try {
            return loanContractRepository.save(contract);
        } catch (Exception e) {
            // 예외 발생 시 로그 기록
            logger.error("문서 저장 중 오류 발생: {}", e.getMessage());
            throw new RuntimeException("문서 저장에 실패했습니다. 관리자에게 문의하세요."); // 클라이언트에 전달할 예외 메시지
        }
    }

    // 문서 업데이트
    public LoanContract updateLoanContract(String id, LoanContract updatedContract, String loggedInUserId) {
        // 기존 차용증 조회
        LoanContract existingContract = loanContractRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("차용증을 찾을 수 없습니다: " + id));

        // 사용자 ID 체크 (옵션: 같은 사용자인지 확인)
        if (!existingContract.getUserId().equals(loggedInUserId)) {
            throw new UnauthorizedException("해당 차용증을 수정할 권한이 없습니다.");
        }

        // 업데이트할 필드 설정
        existingContract.setBorrowerName(updatedContract.getBorrowerName());
        existingContract.setLenderName(updatedContract.getLenderName());
        existingContract.setLoanStartDate(updatedContract.getLoanStartDate());
        existingContract.setLoanEndDate(updatedContract.getLoanEndDate());
        existingContract.setLoanAmount(updatedContract.getLoanAmount());
        existingContract.setInterestRate(updatedContract.getInterestRate());
        existingContract.setLenderIdNumber(updatedContract.getLenderIdNumber());
        existingContract.setLenderAddress(updatedContract.getLenderAddress());
        existingContract.setLenderPhoneNumber(updatedContract.getLenderPhoneNumber());
        existingContract.setBorrowerIdNumber(updatedContract.getBorrowerIdNumber());
        existingContract.setBorrowerAddress(updatedContract.getBorrowerAddress());
        existingContract.setBorrowerPhoneNumber(updatedContract.getBorrowerPhoneNumber());

        // 차용증 업데이트
        return loanContractRepository.save(existingContract);
    }

    // 사용자별 문서 조회
    public List<LoanContract> findContractsByUserId(String userId) {
        return loanContractRepository.findByUserId(userId);
    }
}

