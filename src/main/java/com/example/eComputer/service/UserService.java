package com.example.eComputer.service;

import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }


    public boolean exists(String email) {
        return false;
    }


    public UserEntity save(UserEntity student) {
        return null;
    }
}
