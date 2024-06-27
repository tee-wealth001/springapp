package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

// @Controller // This means that this class is a Controller
@RestController
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/allusers")
    public @ResponseBody List<User> getAllUsers() {
      // This returns a JSON or XML with the users
      return userRepository.findAll();
    }
    
}
