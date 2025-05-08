package edu.epam.fop.spring.boot.exception;

public class ArticleAlreadyExistsException extends RuntimeException {
    public ArticleAlreadyExistsException(String title) {
        super("Article already exists: " + title);
    }
}
