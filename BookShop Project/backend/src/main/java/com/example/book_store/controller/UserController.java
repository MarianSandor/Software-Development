package com.example.book_store.controller;

import com.example.book_store.dto.BookDTO;
import com.example.book_store.dto.UserDTO;
import com.example.book_store.model.User;
import com.example.book_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.book_store.UrlMapping.*;

@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> allUsers() {
        return userService.findAll();
    }

    @GetMapping(ENTITY)
    public UserDTO getItem(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping(SEARCH_USERS + QUERY)
    public List<UserDTO> allUsersBy(@PathVariable String query){
        return userService.search(query);
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO item) {
        return userService.create(item);
    }

    @PutMapping(ENTITY)
    public UserDTO edit(@PathVariable Long id, @RequestBody UserDTO item) {
        return userService.edit(id, item);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
