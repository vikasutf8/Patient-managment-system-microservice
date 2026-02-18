package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.ResourceNotFoundException;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.sharedDto.ApiResponse;
import com.pm.patientservice.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v2/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PatientResponseDto>>>getAllpatient(){

        List<PatientResponseDto> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            throw new ResourceNotFoundException("No patients found");
        }

        return ResponseUtil.ok("All patients fetched successfully", patients);
    }


}
