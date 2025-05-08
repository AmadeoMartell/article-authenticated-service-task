package edu.epam.fop.spring.boot.service;

import edu.epam.fop.spring.boot.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto getByTitle(String title);
    ArticleDto createArticle(ArticleDto articleDto);
    ArticleDto updateArticle(String title, ArticleDto articleDto);
    void deleteByTitle(String title);
    List<ArticleDto> getAll();
}
