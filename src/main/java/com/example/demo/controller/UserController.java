package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.demo.repository.UserRepository;

// @Controller // This means that this class is a Controller
@RestController
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // get all users
    @GetMapping(path = "/allusers")
    public @ResponseBody List<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    // get one user
    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<User> getOneUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // create new user
    @PostMapping(path = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // update a user
    @PutMapping(path = "/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            user.setId(userId); // Ensure the correct ID is set for update
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // delete a user
    @DeleteMapping(path = "/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
