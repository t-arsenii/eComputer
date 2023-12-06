package com.example.eComputer.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Computer_builds")
public class ComputerBuildEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonManagedReference
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
    private String deliverAdderss;
    @JsonManagedReference
    @ManyToOne
    private UserEntity user;
    public ComputerBuildEntity() {
        creationDate = LocalDateTime.now();
        totalPrice = 0.0;
    }
}
