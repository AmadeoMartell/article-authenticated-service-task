package edu.epam.fop.spring.boot.dto;

import edu.epam.fop.spring.boot.entity.User;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */
public record UserDto(Long id, String username, Set<RoleDto> roles) implements Serializable {
}