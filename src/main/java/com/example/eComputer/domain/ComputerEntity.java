package com.example.eComputer.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Computers")

public class ComputerEntity {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;


        private String Motherboard;
        private String RAM;
        private String Hard_drive;
        private String CPU;




}
