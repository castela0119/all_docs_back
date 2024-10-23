package com.alldocs.demo.controller;

import com.alldocs.demo.model.BaseDocument;
import com.alldocs.demo.model.User;
import com.alldocs.demo.service.DocumentService;
import com.alldocs.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private TokenService tokenService;

    // 문서 조회
    @GetMapping("/api/documents/saved")
    public List<BaseDocument> getUserDocuments(@RequestHeader("Authorization") String token) {
        return documentService.getUserDocuments(token); // 토큰으로 사용자 문서 조회
    }

    // 문서 저장 (로그인한 사용자만)
    @PostMapping("/api/documents/save")
    public ResponseEntity<BaseDocument> saveDocument(
            @RequestHeader("Authorization") String token,
            @RequestBody BaseDocument document) {

        // 토큰으로 사용자 인증
        User user = tokenService.getUserByToken(token);
        if (user == null) {
            return ResponseEntity.status(401).body(null); // 인증되지 않은 경우 401 반환
        }

        // 사용자 ID를 문서에 설정
        document.setUserId(user.getId());

        // 문서 저장
        BaseDocument savedDocument = documentService.saveDocument(document);
        return ResponseEntity.ok(savedDocument);
    }
}