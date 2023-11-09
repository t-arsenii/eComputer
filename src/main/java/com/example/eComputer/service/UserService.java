package com.example.eComputer.service;
import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService{
    public UserEntity getUserById(Long id);
//    public UserEntity getStudentByName(String name);
    public List<UserEntity> getAllUsers();
    public boolean exists(String email);
    public UserEntity save(UserEntity student);
}
