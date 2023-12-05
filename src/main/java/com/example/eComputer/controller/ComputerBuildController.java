package com.example.eComputer.controller;

import com.example.eComputer.domain.ComputerBuildEntity;
import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.domain.ComputerPartType;
import com.example.eComputer.dto.ComputerBuildDTO;
import com.example.eComputer.dto.ComputerPartDTO;
import com.example.eComputer.service.ComputerBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/builds")
public class ComputerBuildController {
    @Autowired
    ComputerBuildService computerBuildService;

    @GetMapping
    public List<ComputerBuildEntity> getAllComputerParts() {
        return computerBuildService.getAllBuilds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerBuildEntity> getComputerPartById(@PathVariable Long id) {
        Optional<ComputerBuildEntity> partOptional = computerBuildService.getComputerBuildById(id);
        return partOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComputerBuildEntity> createComputerPart(@RequestBody ComputerBuildDTO computerBuildDTO) {
        ComputerBuildEntity computerBuild = new ComputerBuildEntity();

        computerBuild.setDescription(computerBuildDTO.getDescription());
        computerBuild.setDeliverAdderss(computerBuildDTO.getDeliverAdderss());
        ComputerBuildEntity savedBuild = computerBuildService.createComputerBuild(computerBuild);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBuild);
    }

    @PostMapping("/{buildId}/addPart/{partId}")
    public ResponseEntity<ComputerBuildEntity> addPartToBuild(
            @PathVariable Long buildId,
            @PathVariable Long partId
    ) {
        try {
            ComputerBuildEntity updatedBuild = computerBuildService.addPartToBuild(buildId, partId);
            return ResponseEntity.ok(updatedBuild);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/{buildId}/removePart/{partId}")
    public ResponseEntity<ComputerBuildEntity> removePartFromBuild(
            @PathVariable Long buildId,
            @PathVariable Long partId
    ) {
        try {
            boolean isRemoved = computerBuildService.removePartFromBuild(buildId, partId);
            return isRemoved ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
