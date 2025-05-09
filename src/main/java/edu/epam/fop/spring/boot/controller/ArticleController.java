package edu.epam.fop.spring.boot.controller;

import edu.epam.fop.spring.boot.dto.ArticleDto;
import edu.epam.fop.spring.boot.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{title}")
    public ResponseEntity<String> getArticle(@PathVariable String title) {
        String text = articleService.getByTitle(title).text();
        return ResponseEntity.ok(text);
    }

    @PostMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleDto> createArticle(
            @PathVariable String title,
            @RequestBody String text) {
        ArticleDto created = articleService.createArticle(
                new ArticleDto(null, title, text));
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArticleDto> updateArticle(
            @PathVariable String title,
            @RequestBody String text) {
        ArticleDto updated = articleService.updateArticle(
                title, new ArticleDto(null, title, text));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable String title) {
        articleService.deleteByTitle(title);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAll() {
        return ResponseEntity.ok(articleService.getAll());
    }
}
