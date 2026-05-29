package com.example.banco.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.regex.Pattern;

public class Client {

    private Long id;
    private String identificationType;
    private String identificationNumber;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public Client(String identificationType, String identificationNumber, String firstName, String lastName, String email, LocalDate birthDate) {

        validateAge(birthDate);
        validateNameLength(firstName, lastName);
        validateEmailFormat(email);

        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.createdAt = LocalDateTime.now();
    }


    public Client(Long id, String identificationType, String identificationNumber,
                  String firstName, String lastName, String email, LocalDate birthDate,
                  LocalDateTime createdAt, LocalDateTime updatedAt) {

        validateAge(birthDate);
        validateNameLength(firstName, lastName);
        validateEmailFormat(email);

        this.id = id;
        this.identificationType = identificationType;
        this.identificationNumber = identificationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = LocalDateTime.now();
    }

    private void validateEmailFormat(String email) {
        if (email == null || !Pattern.compile(EMAIL_REGEX).matcher(email).matches()) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido.");
        }
    }


    private void validateAge(LocalDate birthDate) {
        if (Period.between(birthDate, LocalDate.now()).getYears() < 18){
            throw new IllegalArgumentException("el cliente debe ser mayor de edad.");
        }
    }

    private void validateNameLength(String firstName, String lastName) {
        if (firstName.length() < 2 || lastName.length() < 2) {
            throw new IllegalArgumentException("El nombre no debe tener menos 2 caracteres.");
        }
    }

    public void updateData(String firstName, String lastName, String email, String identificationNumber, String identificationType) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identificationNumber = identificationNumber;
        this.identificationType = identificationType;

    }

    public Long getId() {
        return id;
    }

    public String getIdentificationType() {
        return identificationType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
