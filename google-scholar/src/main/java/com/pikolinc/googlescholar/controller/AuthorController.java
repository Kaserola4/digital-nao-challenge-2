package com.pikolinc.googlescholar.controller;

import com.pikolinc.googlescholar.dto.AuthorResponse;
import com.pikolinc.googlescholar.service.GoogleScholarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/author")
class AuthorController {
    private final GoogleScholarService googleScholarService;

    AuthorController(GoogleScholarService googleScholarService) {
        this.googleScholarService = googleScholarService;
    }

    @GetMapping("/{authorId}")
    public AuthorResponse getAuthorById(@PathVariable String authorId) {
        return googleScholarService.getAuthorById(authorId);
    }

}
