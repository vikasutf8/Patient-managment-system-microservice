package com.pm.patientservice.dto;

import com.pm.patientservice.sharedDto.ValidateRequestDto.onCreatePatient;
import com.pm.patientservice.sharedDto.ValidateRequestDto.onUpdatePatient;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRequestDto {

//    @NotBlank(message = "Name is required")
//    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
//    private String name;
//
//    @NotBlank(message = "Email is required")
//    @Email(message = "Invalid email format")
//    private String email;
//
//    @NotBlank(message = "Address is required")
//    @Size(max = 255, message = "Address must not exceed 255 characters")
//    private String address;
//
//    @NotNull(message = "Date of birth is required")
//    @Past(message = "Date of birth must be in the past")
//    private LocalDate dob;
//
//    @NotNull(message = "Registered date is required")
//    @PastOrPresent(message = "Registered date cannot be in the future")
//    private LocalDate registeredDate;




        @NotBlank(message = "Name is required", groups = {onCreatePatient.class, onUpdatePatient.class})
        @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters",
                groups = {onCreatePatient.class, onUpdatePatient.class})
        private String name;

        @NotBlank(message = "Email is required", groups = {onCreatePatient.class})
        @Email(message = "Invalid email format", groups = {onCreatePatient.class, onUpdatePatient.class})
        private String email;

        @NotBlank(message = "Address is required", groups = {onCreatePatient.class, onUpdatePatient.class})
        @Size(max = 255, message = "Address must not exceed 255 characters",
                groups = {onCreatePatient.class, onUpdatePatient.class})
        private String address;

        @NotNull(message = "Date of birth is required", groups = {onCreatePatient.class})
        @Past(message = "Date of birth must be in the past",
                groups = {onCreatePatient.class, onUpdatePatient.class})
        private LocalDate dob;

        @NotNull(message = "Registered date is required", groups = {onCreatePatient.class})
        @PastOrPresent(message = "Registered date cannot be in the future",
                groups = {onCreatePatient.class, onUpdatePatient.class})
        private LocalDate registeredDate;


}

