package com.example.eComputer.repository;

import com.example.eComputer.domain.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
//Interface segregation
@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByName(String firstName);
    public List<UserEntity> findAll();
    UserEntity findByEmail (String email);
}
