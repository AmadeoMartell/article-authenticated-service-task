package edu.epam.fop.spring.boot.service;

import edu.epam.fop.spring.boot.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto createUser(UserDto userDto, String rawPassword);
    Optional<UserDto> findByUsername(String username);
    List<UserDto> getAllUsers();
}
