package com.example.pcpartshop.controller;

import com.example.pcpartshop.dto.ConfigurationDto;
import com.example.pcpartshop.dto.user.UserDTO;
import com.example.pcpartshop.service.ConfigurationService;
import com.example.pcpartshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.lang.module.Configuration;
import java.util.List;

import static com.example.pcpartshop.UrlMapping.*;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ConfigurationService configurationService;

    @GetMapping
    public List<UserDTO> allUsers() {
        return userService.findAll();
    }

    @GetMapping(ENTITY)
    public UserDTO getUser(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping(SEARCH + QUERY)
    public List<UserDTO> allUsersBy(@PathVariable String query){
        return userService.search(query);
    }

    @GetMapping(ENTITY + "/configurations")
    public List<ConfigurationDto> configurationsForUser(@PathVariable Long id){
        return configurationService.configurationForUser(id);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO item) {
        return userService.create(item);
    }

    @PutMapping(ENTITY)
    public UserDTO edit(@PathVariable Long id, @RequestBody UserDTO user) {
        return userService.edit(id, user);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
