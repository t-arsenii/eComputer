package com.example.eComputer.service;

import com.example.eComputer.domain.ComputerBuildEntity;
import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.repository.ComputerBuildRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComputerBuildService {
    @Autowired
    ComputerBuildRepository computerBuildRepository;
    public List<ComputerBuildEntity> getAllBuilds() {
        return computerBuildRepository.findAll();
    }
    public Optional<ComputerBuildEntity> getComputerBuildById(Long id) {
        return computerBuildRepository.findById(id);
    }
    public ComputerBuildEntity createComputerBuild(ComputerBuildEntity computerBuild) {
        return computerBuildRepository.save(computerBuild);
    }
}
