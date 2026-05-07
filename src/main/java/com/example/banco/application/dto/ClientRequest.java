package com.example.banco.application.dto;

import java.time.LocalDate;

public record ClientRequest(
        String identificationType,
        String identificationNumber,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate

) {}
