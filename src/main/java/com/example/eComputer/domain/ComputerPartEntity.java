package com.example.eComputer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Computer_parts")
public class ComputerPartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Double price;
    private ComputerPartType type;
    private int amountLeft;

    @ManyToMany(mappedBy = "parts")
    private Set<ComputerBuildEntity> computers;
}
