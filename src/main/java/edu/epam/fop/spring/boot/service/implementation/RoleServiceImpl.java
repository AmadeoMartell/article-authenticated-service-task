package edu.epam.fop.spring.boot.service.implementation;

import edu.epam.fop.spring.boot.dto.RoleDto;
import edu.epam.fop.spring.boot.entity.Role;
import edu.epam.fop.spring.boot.mapper.RoleMapper;
import edu.epam.fop.spring.boot.repository.RoleRepository;
import edu.epam.fop.spring.boot.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    @Transactional
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleRepository.findByName(roleDto.name())
                .orElseGet(() -> roleRepository.save(roleMapper.toEntity(roleDto)));
        return roleMapper.toDto(role);
    }

    @Override
    public Optional<RoleDto> findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toDto);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }
}
