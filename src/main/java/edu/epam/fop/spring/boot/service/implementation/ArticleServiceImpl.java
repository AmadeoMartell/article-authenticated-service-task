package edu.epam.fop.spring.boot.service.implementation;

import edu.epam.fop.spring.boot.dto.ArticleDto;
import edu.epam.fop.spring.boot.entity.Article;
import edu.epam.fop.spring.boot.mapper.ArticleMapper;
import edu.epam.fop.spring.boot.repository.ArticleRepository;
import edu.epam.fop.spring.boot.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public ArticleDto getByTitle(String title) {
        Article article = articleRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + title));
        return articleMapper.toDto(article);
    }

    @Override
    @Transactional
    public ArticleDto createArticle(ArticleDto articleDto) {
        String title = articleDto.title();
        if (articleRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("Article already exists: " + title);
        }
        Article entity = articleMapper.toEntity(articleDto);
        Article saved = articleRepository.save(entity);
        return articleMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ArticleDto updateArticle(String title, ArticleDto articleDto) {
        Article existing = articleRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + title));
        articleMapper.partialUpdate(articleDto, existing);
        Article saved = articleRepository.save(existing);
        return articleMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteByTitle(String title) {
        Article existing = articleRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + title));
        articleRepository.delete(existing);
    }

    @Override
    public List<ArticleDto> getAll() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDto)
                .collect(Collectors.toList());
    }
}
