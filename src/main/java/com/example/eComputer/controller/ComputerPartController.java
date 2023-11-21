package com.example.eComputer.controller;

import com.example.eComputer.domain.ComputerPartEntity;
import com.example.eComputer.service.ComputerPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parts")
public class ComputerPartController {

    @Autowired
    private ComputerPartService computerPartService;

    @GetMapping
    public List<ComputerPartEntity> getAllComputerParts() {
        return computerPartService.getAllComputerParts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComputerPartEntity> getComputerPartById(@PathVariable Long id) {
        Optional<ComputerPartEntity> partOptional = computerPartService.getComputerPartById(id);
        return partOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ComputerPartEntity> createComputerPart(@RequestBody ComputerPartEntity computerPart) {
        ComputerPartEntity savedPart = computerPartService.createComputerPart(computerPart);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPart);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComputerPartEntity> updateComputerPart(@PathVariable Long id, @RequestBody ComputerPartEntity updatedPart) {
        Optional<ComputerPartEntity> partOptional = computerPartService.updateComputerPart(id, updatedPart);
        return partOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComputerPart(@PathVariable Long id) {
        boolean deleted = computerPartService.deleteComputerPart(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
