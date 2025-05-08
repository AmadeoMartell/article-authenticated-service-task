package edu.epam.fop.spring.boot.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String title) {
        super("Article not found: " + title);
    }
}
