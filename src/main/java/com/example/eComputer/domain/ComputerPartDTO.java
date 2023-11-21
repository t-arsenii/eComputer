package com.example.eComputer.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComputerPartDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private ComputerPartType type;
    private int amountLeft;
}
