package edu.epam.fop.spring.boot.service.implementation;

import edu.epam.fop.spring.boot.dto.RoleDto;
import edu.epam.fop.spring.boot.dto.UserDto;
import edu.epam.fop.spring.boot.entity.Role;
import edu.epam.fop.spring.boot.entity.User;
import edu.epam.fop.spring.boot.mapper.RoleMapper;
import edu.epam.fop.spring.boot.mapper.UserMapper;
import edu.epam.fop.spring.boot.repository.UserRepository;
import edu.epam.fop.spring.boot.service.RoleService;
import edu.epam.fop.spring.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto, String rawPassword) {
        String username = userDto.username();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));

        RoleDto userRoleDto = roleService.createRole(new RoleDto(null, "USER"));
        Role userRoleEntity = roleMapper.toEntity(userRoleDto);

        user.setRoles(Set.of(userRoleEntity));

        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto);
    }

    @Override
    public java.util.List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}
