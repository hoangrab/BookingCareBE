package com.n7.controller;

import com.n7.dto.ArticleDTO;
import com.n7.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/")
public class ArticleController {
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/articles")
    public ResponseEntity<?> getAllArticle() {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@Valid @RequestBody ArticleDTO articleDTO,
                                           @RequestPart("file")MultipartFile multipartFile) {
        return ResponseEntity.ok().body(new SuccessResponse<ArticleDTO>("ok",articleDTO));
    }

    @PutMapping("/article")
    public ResponseEntity<?> updateArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok().body(new SuccessResponse<ArticleDTO>("ok",articleDTO));
    }
}
