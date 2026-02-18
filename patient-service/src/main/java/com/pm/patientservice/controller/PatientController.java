package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.ResourceNotFoundException;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.sharedDto.ApiResponse;
import com.pm.patientservice.sharedDto.PageResponse;
import com.pm.patientservice.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v2/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<PageResponse<PatientResponseDto>>>getAllpatient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

//        List<PatientResponseDto> patients = patientService.getAllPatients();
        Page<PatientResponseDto> patients =
                patientService.getAllPatients(PageRequest.of(page, size));

        PageResponse<PatientResponseDto> pageResponse =
                PageResponse.<PatientResponseDto>builder()
                        .content(patients.getContent())
                        .page(patients.getNumber())
                        .size(patients.getSize())
                        .totalElements(patients.getTotalElements())
                        .totalPages(patients.getTotalPages())
                        .last(patients.isLast())
                        .build();

        return ResponseUtil.ok("All patients fetched successfully", pageResponse);
    }


}
