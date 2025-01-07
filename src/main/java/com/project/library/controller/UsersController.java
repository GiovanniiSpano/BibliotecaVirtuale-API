package com.project.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.library.entity.User;
import com.project.library.service.UsersService;

import io.micrometer.common.util.StringUtils;

public class UsersController {
    
    final private UsersService usersService;

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/users") 
    public Page<User> getAllUsers(@RequestParam(value="offset") Integer offset, @RequestParam(value="pageSize") Integer pageSize, @RequestParam(value="sortBy") String sortBy) {
        if (offset == null) offset = 0;
        if (pageSize == null) pageSize = 10;
        if (StringUtils.isEmpty(sortBy)) sortBy = "id";

        return this.usersService.getUsersPage(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return this.usersService.getUserByUsername(username);
    }

    @PostMapping("/users/register")
    public User registerUser(@RequestBody User user) {
        return this.usersService.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        return this.usersService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable("id") Integer id) {
        return this.usersService.deleteUser(id);
    }
}
