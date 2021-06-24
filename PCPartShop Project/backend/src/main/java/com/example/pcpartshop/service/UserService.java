package com.example.pcpartshop.service;

import com.example.pcpartshop.dto.user.UserDTO;
import com.example.pcpartshop.mapper.UserMapper;
import com.example.pcpartshop.model.Role;
import com.example.pcpartshop.model.User;
import com.example.pcpartshop.repository.RoleRepository;
import com.example.pcpartshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.pcpartshop.repository.specification.UserSpecification.emailLike;
import static com.example.pcpartshop.repository.specification.UserSpecification.usernameLike;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    private User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    public UserDTO get(Long id) {
        return userMapper.toDTO(findById(id));
    }

    public List<UserDTO> findAll() {
        List<User> us = userRepository.findAll();
        userMapper.toDTO(us.get(0));
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> search(String query) {
        return userRepository.findAll(usernameLike(query).or(emailLike(query))).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO create(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(encoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .build();

        Role role = roleRepository.findByName(userDTO.getRole()).orElseThrow(() -> new RuntimeException("Cannot find role: " + userDTO.getRole()));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        user.setRoles(roles);

        return userMapper.toDTO(userRepository.save(user));
    }

    public UserDTO edit(Long id, UserDTO user) {
        User currUser = findById(id);

        currUser.setEmail(user.getEmail());
        currUser.setUsername(user.getUsername());

        Role role = roleRepository.findByName(user.getRole()).orElseThrow(() -> new RuntimeException("Cannot find role: " + user.getRole()));

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        currUser.setRoles(roles);

        return userMapper.toDTO(
                userRepository.save(currUser)
        );
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
