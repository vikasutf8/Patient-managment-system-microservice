package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.ResourceNotFoundException;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public Page<PatientResponseDto> getAllPatients(Pageable pageable) {

        org.springframework.data.domain.Page<Patient> patientPage = patientRepository.findAll(pageable);

        if (patientPage.isEmpty()) {
            throw new ResourceNotFoundException("No patients found");
        }

        return patientPage.map(this::mapToDto);
    }

    private PatientResponseDto mapToDto(Patient patient) {
        return modelMapper.map(patient, PatientResponseDto.class);
    }
}
