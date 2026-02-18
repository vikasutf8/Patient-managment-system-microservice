package com.pm.patientservice.service;

import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;


    public List<PatientResponseDto> getAllPatients() {
        List<Patient> allpatient =patientRepository.findAll();

        return Collections.singletonList(modelMapper.map(allpatient, PatientResponseDto.class));

    }
}
