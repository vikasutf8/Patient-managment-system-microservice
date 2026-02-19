package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.AlreadyExistsException;
import com.pm.patientservice.exception.ResourceNotFoundException;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    private PatientResponseDto mapToDto(Patient patient) {
        return modelMapper.map(patient, PatientResponseDto.class);
    }

    public Page<PatientResponseDto> getAllPatients(Pageable pageable) {

        org.springframework.data.domain.Page<Patient> patientPage = patientRepository.findAll(pageable);

        if (patientPage.isEmpty()) {
            throw new ResourceNotFoundException("No patients found");
        }

        return patientPage.map(this::mapToDto);
    }




    @Transactional
    public PatientResponseDto createNewPatient(@Valid PatientRequestDto request) {

        // 1️⃣ Business validation
        if (patientRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Patient with this email already exists");
        }

        // 2️⃣ Map DTO → Entity
        Patient patient = modelMapper.map(request, Patient.class);

        // Optional: set system-generated fields
        patient.setRegisteredDate(LocalDate.now());

        // 3️⃣ Save to DB
        Patient savedPatient = patientRepository.save(patient);

        // 4️⃣ Map Entity → Response DTO
        return modelMapper.map(savedPatient, PatientResponseDto.class);
    }

    public PatientResponseDto updatePatient(UUID id, @Valid PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));

        // update fields
        patient.setName(requestDto.getName());
        patient.setEmail(requestDto.getEmail());
        patient.setAddress(requestDto.getAddress());
        patient.setDob(requestDto.getDob());

        Patient savedPatient = patientRepository.save(patient);

        return mapToDto(savedPatient);
    }

    public void deletePatient(UUID id) {

            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Patient not found with id: " + id));

            patientRepository.delete(patient);

    }
}
