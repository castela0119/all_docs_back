package com.alldocs.demo.service;

import com.alldocs.demo.model.BaseDocument;
import com.alldocs.demo.model.User;
import com.alldocs.demo.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private TokenService tokenService;

    // 토큰을 기반으로 사용자가 저장한 문서 조회
    public List<BaseDocument> getUserDocuments(String token) {
        // 토큰을 이용해 사용자 조회
        User user = tokenService.getUserByToken(token);
        if (user == null) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        // 해당 사용자가 저장한 문서 조회
        return documentRepository.findByUserId(user.getId());
    }

    // 특정 사용자가 저장한 모든 문서 조회
    public List<BaseDocument> getAllDocumentsByUser(String userId) {
        return documentRepository.findByUserId(userId);
    }

    // 문서 삭제
    public void deleteDocumentById(String documentId, String userId) {
        BaseDocument document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("해당 문서를 찾을 수 없습니다."));
        if (!document.getUserId().equals(userId)) {
            throw new RuntimeException("문서를 삭제할 권한이 없습니다.");
        }
        documentRepository.delete(document);
    }

    // 문서 저장
    public BaseDocument saveDocument(BaseDocument document) {
        return documentRepository.save(document);
    }
}
