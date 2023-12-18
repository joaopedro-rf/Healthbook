package com.example.Healthbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Entity
@Table(name ="PACIENT")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Pacient's name must not be empty")
    private String name;
    @NotNull(message = "Age should not be null")
    @Positive(message = "Age must be a positive number")
    private int age;
    @NotEmpty(message = "Phone number must not be empty")
    private String phoneNumber;
    @NotEmpty(message = "Address must not be empty")
    private String address;

}
