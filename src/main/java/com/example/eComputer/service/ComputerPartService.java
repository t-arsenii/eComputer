package com.example.eComputer.service;

import com.example.eComputer.domain.ComputerPartEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ComputerPartService {
    List<ComputerPartEntity> getAllComputerParts();
    Optional<ComputerPartEntity> getComputerPartById(Long id);
    ComputerPartEntity createComputerPart(ComputerPartEntity computerPart);
    Optional<ComputerPartEntity> updateComputerPart(Long id, ComputerPartEntity updatedPart);
    boolean deleteComputerPart(Long id);
}
