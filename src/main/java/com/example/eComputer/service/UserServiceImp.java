package com.example.eComputer.service;

import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.domain.enums.Role;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Service
@Transactional
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean exists(String email) {
        return false;
    }

    @Override
    public UserEntity save(UserEntity student) {

        return null;
    }

    public boolean createUser(UserEntity student) {
        String email = student.getEmail();
        if (userRepository.findByEmail(email)!= null) return false;
        student.setActive(true);
        student.getRoles().add(Role.ROLE_USER);
        return true;
    }
}
