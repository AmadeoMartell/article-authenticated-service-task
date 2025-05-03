package edu.epam.fop.spring.boot.dto;

import java.io.Serializable;

/**
 * DTO for {@link edu.epam.fop.spring.boot.entity.Role}
 */
public record RoleDto(Long id, String name) implements Serializable {
}