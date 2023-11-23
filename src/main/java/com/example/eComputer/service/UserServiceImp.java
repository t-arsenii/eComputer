package com.example.eComputer.service;

import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.domain.enums.Role;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean createUser(UserEntity student) {
        if (userRepository.findByEmail(student.getEmail())!= null) return false;
        student.setActive(true);
        student.getRoles().add(Role.ROLE_USER);

    }
}
