package edu.epam.fop.spring.boot.service;

import edu.epam.fop.spring.boot.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);
    Optional<RoleDto> findByName(String name);
    List<RoleDto> getAllRoles();
}
