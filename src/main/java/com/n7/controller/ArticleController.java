package com.n7.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class ArticleController {
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticle() {
        return ResponseEntity.ok().body(null);
    }
}
