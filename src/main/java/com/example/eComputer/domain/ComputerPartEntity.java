package com.example.eComputer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @JsonBackReference
    @ManyToMany(mappedBy = "parts")
    private Set<ComputerBuildEntity> computers;
}
