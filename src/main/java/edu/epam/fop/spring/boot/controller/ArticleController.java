package edu.epam.fop.spring.boot.controller;

import edu.epam.fop.spring.boot.dto.ArticleDto;
import edu.epam.fop.spring.boot.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class ArticleController {

    private final ArticleService articleService;

    // In this task these endpoints was given by requirements. Not a good example for Rest API Architecture
    @GetMapping("/{title}")
    public String get(@PathVariable String title) {
        return articleService.getByTitle(title).text();
    }

    @PostMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDto create(
            @PathVariable String title,
            @RequestBody String text) {
        return articleService.createArticle(new ArticleDto(null, title, text));
    }

    @PutMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    public ArticleDto update(
            @PathVariable String title,
            @RequestBody String text) {
        return articleService.updateArticle(
                title, new ArticleDto(null, title, text));
    }

    @DeleteMapping("/{title}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String title) {
        articleService.deleteByTitle(title);
    }

    @GetMapping
    public List<ArticleDto> getAll() {
        return articleService.getAll();
    }
}
