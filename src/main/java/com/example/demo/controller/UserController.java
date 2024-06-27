package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

// @Controller // This means that this class is a Controller
@RestController
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserService userService;

    // get all users
    @GetMapping(path = "/allusers")
    public @ResponseBody List<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userService.getAllUsers();
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Map<String, Object>> getOneUser(@PathVariable Long userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok().body(
                    Map.of(
                            "status", 200,
                            "message", "User found",
                            "data", userOptional.get()));
        } else {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found",
                            "data", null));
        }
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(
                Map.of(
                        "status", 201,
                        "message", "User created",
                        "data", createdUser));
    }

    @PutMapping(path = "/user/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long userId, @RequestBody User user) {
        User updatedUser = userService.updateUser(userId, user);
        if (updatedUser != null) {
            return ResponseEntity.ok().body(
                    Map.of(
                            "status", 200,
                            "message", "User updated",
                            "data", updatedUser));
        } else {
            return ResponseEntity.status(404).body(
                    Map.of(
                            "status", 404,
                            "error", "Not Found",
                            "message", "User not found",
                            "data", null));
        }
    }

    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(204).body(
                Map.of(
                        "status", 204,
                        "message", "User deleted",
                        "data", null));
    }
}