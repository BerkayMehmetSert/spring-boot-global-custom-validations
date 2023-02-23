package com.bms.globalcustomvalidations.controller;

import com.bms.globalcustomvalidations.model.User;
import com.bms.globalcustomvalidations.service.UserService;
import com.bms.globalcustomvalidations.service.request.CreateUserRequest;
import com.bms.globalcustomvalidations.service.request.UpdateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(CreateUserRequest request) {
        userService.createUser(request);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
