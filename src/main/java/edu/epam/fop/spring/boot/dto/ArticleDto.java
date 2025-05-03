package edu.epam.fop.spring.boot.dto;

import java.io.Serializable;

/**
 * DTO for {@link edu.epam.fop.spring.boot.entity.Article}
 */
public record ArticleDto(Long id, String title, String text) implements Serializable {
}