package com.example.eComputer.service;

import com.example.eComputer.domain.ComputerBuildEntity;
import com.example.eComputer.domain.UserEntity;

import java.util.List;
import java.util.Optional;

public interface ComputerBuildService {
    public List<ComputerBuildEntity> getAllBuilds();
    public Optional<ComputerBuildEntity> getComputerBuildById(Long id);
    public ComputerBuildEntity createComputerBuild(ComputerBuildEntity computerBuild, UserEntity user);
    public ComputerBuildEntity addPartToBuild(Long buildId, Long partId);
    public boolean removePartFromBuild(Long buildId, Long partId);
}
