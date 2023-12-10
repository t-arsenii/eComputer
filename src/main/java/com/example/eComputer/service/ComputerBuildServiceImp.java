package com.example.eComputer.service;

import com.example.eComputer.domain.ComputerBuildEntity;
import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.domain.UserEntity;
import com.example.eComputer.repository.ComputerBuildRepository;
import com.example.eComputer.repository.ComputerPartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ComputerBuildServiceImp implements ComputerBuildService{
    @Autowired
    ComputerBuildRepository computerBuildRepository;
    @Autowired
    ComputerPartRepository computerPartRepository;

    public boolean deleteComputerBuild(Long id) {
        if (computerBuildRepository.existsById(id)) {
            computerBuildRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public List<ComputerBuildEntity> getAllBuilds() {
        return computerBuildRepository.findAll();
    }

    public Optional<ComputerBuildEntity> getComputerBuildById(Long id) {
        return computerBuildRepository.findById(id);
    }

    public ComputerBuildEntity createComputerBuild(ComputerBuildEntity computerBuild, UserEntity user) {
        computerBuild.setUser(user);
        return computerBuildRepository.save(computerBuild);
    }

    public ComputerBuildEntity addPartToBuild(Long buildId, Long partId) {
        ComputerBuildEntity build = computerBuildRepository.findById(buildId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid build ID"));

        ComputerPartEntity partToAdd = computerPartRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part ID"));
        boolean partOfTypeExists = build.getParts().stream()
                .anyMatch(part -> part.getType().equals(partToAdd.getType()));

        if (partOfTypeExists) {
            throw new IllegalStateException("A part of this type already exists in the build.");
        }
        build.getParts().add(partToAdd);
        partToAdd.getComputers().add(build);

        build.setTotalPrice(build.getTotalPrice() + partToAdd.getPrice());
        partToAdd.setAmountLeft(partToAdd.getAmountLeft() - 1);

        computerPartRepository.save(partToAdd);
        return computerBuildRepository.save(build);
    }
    public boolean removePartFromBuild(Long buildId, Long partId) {
        ComputerBuildEntity build = computerBuildRepository.findById(buildId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid build ID"));

        ComputerPartEntity partToRemove = computerPartRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid part ID"));

        boolean removed = build.getParts().removeIf(part -> part.getId().equals(partId));
        if (removed) {
            partToRemove.getComputers().remove(build);

            build.setTotalPrice(build.getTotalPrice() - partToRemove.getPrice());
            partToRemove.setAmountLeft(partToRemove.getAmountLeft() + 1);

            computerPartRepository.save(partToRemove);
            computerBuildRepository.save(build);
        }
        return removed;
    }
}
