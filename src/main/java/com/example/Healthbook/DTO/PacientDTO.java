package com.example.Healthbook.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class PacientDTO {

    @NotEmpty(message = "Pacient's name must not be empty")
    String name;
    @NotNull(message = "Age should not be null")
    @Positive(message = "Age must be a positive number")
    int age;
    @NotEmpty(message = "Phone number must not be empty")
    String phoneNumber;
    @NotEmpty(message = "Address must not be empty")
    String address;
}
