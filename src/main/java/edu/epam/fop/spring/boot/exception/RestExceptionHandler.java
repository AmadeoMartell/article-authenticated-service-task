package edu.epam.fop.spring.boot.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(ArticleNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ArticleAlreadyExistsException.class)
    public ResponseEntity<Void> handleConflict(ArticleAlreadyExistsException ex) {
        return ResponseEntity.status(409).build();
    }

}
