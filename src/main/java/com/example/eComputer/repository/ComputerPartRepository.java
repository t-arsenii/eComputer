package com.example.eComputer.repository;

import com.example.eComputer.domain.ComputerPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//Interface segregation
@Repository
public interface ComputerPartRepository extends JpaRepository<ComputerPartEntity, Long> {
}
