package com.example.eComputer.controller;

import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/students")
    public ResponseEntity<UserEntity>
    createStudent(@RequestBody UserEntity user) {
        HttpStatus status = HttpStatus.CREATED;
        UserEntity saved = userService.save(user);
        return new ResponseEntity<>(saved, status);
    }
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @GetMapping("/students")
    public List<UserEntity> getAllStudents() {
        return userService.getAllUsers();
    }
}
