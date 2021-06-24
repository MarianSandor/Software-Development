package com.example.book_store.service;

import com.example.book_store.dto.BookDTO;
import com.example.book_store.dto.UserDTO;
import com.example.book_store.mapper.UserMapper;
import com.example.book_store.model.Role;
import com.example.book_store.model.User;
import com.example.book_store.repository.RoleRepository;
import com.example.book_store.repository.UserRepository;
import com.example.book_store.repository.specifications.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.book_store.repository.specifications.BookSpecifications.*;
import static com.example.book_store.repository.specifications.UserSpecifications.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

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
        String likeQuery = "%" + query + "%";

        return userRepository.findAll(usernameLike(likeQuery).or(emailLike(likeQuery))).stream()
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
