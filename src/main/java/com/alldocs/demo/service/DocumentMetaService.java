package com.alldocs.demo.service;

import com.alldocs.demo.model.DocumentMeta;
import com.alldocs.demo.repository.DocumentMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentMetaService {

    @Autowired
    private DocumentMetaRepository documentMetaRepository;

    // 상위 메타데이터 생성 및 저장
    public DocumentMeta createDocumentMeta(String documentId, String documentType, String userId, String title) {
        DocumentMeta documentMeta = new DocumentMeta();
        documentMeta.setId(UUID.randomUUID().toString()); // 메타데이터 ID 생성
        documentMeta.setDocumentId(documentId); // 실제 문서 ID
        documentMeta.setDocumentType(documentType); // 문서 타입 (예: LoanContract)
        documentMeta.setUserId(userId); // 사용자 ID
        documentMeta.setTitle(title); // 문서 제목
        documentMeta.setCreatedAt(new Date().toString()); // 생성 시간

        return documentMetaRepository.save(documentMeta); // MongoDB에 저장
    }

    // ID로 메타데이터 조회
    public DocumentMeta getDocumentMetaById(String id) {
        return documentMetaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 문서 메타데이터를 찾을 수 없습니다."));
    }

    // 사용자 ID로 모든 메타데이터 조회
    public List<DocumentMeta> getDocumentsByUserId(String userId) {
        return documentMetaRepository.findByUserId(userId);
    }

    // 모든 문서 메타데이터 조회
    public List<DocumentMeta> getAllDocuments() {
        return documentMetaRepository.findAll();
    }

    // 문서 메타데이터 삭제
    public void deleteDocumentMeta(String id) {
        DocumentMeta meta = getDocumentMetaById(id); // 존재 여부 확인
        documentMetaRepository.delete(meta);
    }
}

