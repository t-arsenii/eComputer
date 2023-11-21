package com.example.eComputer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Computer_builds")
public class ComputerBuildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "Computer_Build_Parts",
            joinColumns = @JoinColumn(name = "computer_build_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<ComputerPartEntity> parts;

    private String description;
    private Double totalPrice;
    private LocalDateTime creationDate;
    private String status;
}
