package edu.epam.fop.spring.boot.repository;

import edu.epam.fop.spring.boot.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findByTitle(String title);
    boolean existsByTitle(String title);
}
