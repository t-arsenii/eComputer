package com.example.eComputer.service;

import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.domain.enums.Role;
import com.example.eComputer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
    public boolean save(UserEntity user) {
        try {
            userRepository.save(user);
            user.setActive(true);
            user.getRoles().add(Role.USER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public boolean addAdmin(UserEntity user) {
        try {
            user.setActive(true);
            user.getRoles().add(Role.ADMIN);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public UserEntity getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
