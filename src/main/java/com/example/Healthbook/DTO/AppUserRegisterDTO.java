package com.example.Healthbook.DTO;

import jakarta.validation.constraints.NotNull;

public record AppUserRegisterDTO(
        @NotNull String username,
        @NotNull String email,
        @NotNull String password
        ) {

}
