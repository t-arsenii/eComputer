package com.example.eComputer.repository;

import com.example.eComputer.domain.ComputerBuildEntity;
import com.example.eComputer.domain.ComputerPartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerBuildRepository extends JpaRepository<ComputerBuildEntity, Long>{}
