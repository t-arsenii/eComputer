package com.example.eComputer.service;

import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.repository.ComputerPartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//Open closed
@Service
@Transactional
public class ComputerPartsServiceImp {
    @Autowired
    ComputerPartRepository computerPartRepository;

    public List<ComputerPartEntity> getAllComputerParts() {
        return computerPartRepository.findAll();
    }

    public Optional<ComputerPartEntity> getComputerPartById(Long id) {
        return computerPartRepository.findById(id);
    }

    public ComputerPartEntity createComputerPart(ComputerPartEntity computerPart) {
        return computerPartRepository.save(computerPart);
    }

    public Optional<ComputerPartEntity> updateComputerPart(Long id, ComputerPartEntity updatedPart) {
        if (computerPartRepository.existsById(id)) {
            updatedPart.setId(id);
            return Optional.of(computerPartRepository.save(updatedPart));
        }
        return Optional.empty();
    }

    public boolean deleteComputerPart(Long id) {
        if (computerPartRepository.existsById(id)) {
            computerPartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
