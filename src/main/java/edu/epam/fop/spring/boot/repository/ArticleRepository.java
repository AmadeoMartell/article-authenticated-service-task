package edu.epam.fop.spring.boot.repository;

import edu.epam.fop.spring.boot.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
    boolean existsByTitle(String title);
}
