package com.betrybe.alexandria.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserCreationDto(
    @NotBlank(message = "Name is required")
    String name,

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    String email,

    @Size(min = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Password is required")
    String password,

   @NotBlank(message = "Cpf is required")
   String cpf,

    @Past(message = "Birthday must be a past date")
    LocalDate birthday,

    LocalDateTime creationDate
) {
}
