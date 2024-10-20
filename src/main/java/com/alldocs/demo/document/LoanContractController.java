package com.alldocs.demo.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class LoanContractController {

    @Autowired
    private LoanContractService loanContractService;

    // 문서 저장
    @PostMapping
    public ResponseEntity<String> createContract(@RequestBody LoanContract contract, Principal principal) {
        try {
            // 인증되지 않은 경우 401 반환
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증이 필요합니다."); // 사용자 인증 실패 시 401 응답
            }

            // principal.getName()으로 사용자 ID 가져오기
            String loggedInUserId = principal.getName();

            // 문서 저장 로직 실행
            LoanContract savedContract = loanContractService.saveLoanContract(contract, loggedInUserId);
            return ResponseEntity.ok("문서가 성공적으로 저장되었습니다."); // 성공 메시지 반환
        } catch (IllegalArgumentException e) {
            // 입력 값이 잘못된 경우
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 입력 값입니다: " + e.getMessage());
        } catch (Exception e) {
            // 그 외의 예외 발생 시
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("문서 저장 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    // 사용자별 문서 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanContract>> getContractsByUserId(@PathVariable String userId) {
        List<LoanContract> contracts = loanContractService.findContractsByUserId(userId);
        return ResponseEntity.ok(contracts);
    }

    // 문서 업데이트
    @PutMapping("/{id}")
    public ResponseEntity<LoanContract> updateContract(@PathVariable String id,
                                                       @RequestBody LoanContract updatedContract,
                                                       Principal principal) {
        String loggedInUserId = principal.getName(); // 로그인된 사용자 ID 가져오기
        LoanContract updatedLoanContract = loanContractService.updateLoanContract(id, updatedContract, loggedInUserId);
        return ResponseEntity.ok(updatedLoanContract);
    }
}


