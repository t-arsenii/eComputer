package com.example.eComputer.service;
import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService extends UserDetailsService {
    public UserEntity getUserById(Long id);
    public List<UserEntity> getAllUsers();
    public boolean exists(String email);
    public boolean save(UserEntity student);
    public boolean addAdmin(UserEntity user);

}
