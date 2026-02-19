package com.pm.patientservice.controller;


import com.pm.patientservice.dto.PatientRequestDto;
import com.pm.patientservice.dto.PatientResponseDto;
import com.pm.patientservice.exception.ResourceNotFoundException;
import com.pm.patientservice.service.PatientService;
import com.pm.patientservice.sharedDto.ApiResponse;
import com.pm.patientservice.sharedDto.PageResponse;
import com.pm.patientservice.sharedDto.ValidateRequestDto.onCreatePatient;
import com.pm.patientservice.sharedDto.ValidateRequestDto.onUpdatePatient;
import com.pm.patientservice.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


//@Tag(name = "Patient", description = "Patient management APIs")
@RestController
@RequestMapping("api/v2/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

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


    @PostMapping
    public ResponseEntity<ApiResponse<PatientResponseDto>> createNewPatient(
            @Validated(onCreatePatient.class) @RequestBody PatientRequestDto request
    ) {

        PatientResponseDto response = patientService.createNewPatient(request);

        return ResponseUtil.created("Patient created successfully", response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<PatientResponseDto>> updatePatient(
            @PathVariable UUID id,
            @Validated(onUpdatePatient.class) @RequestBody PatientRequestDto requestDto) {

        PatientResponseDto updatedPatient = patientService.updatePatient(id, requestDto);


        return ResponseUtil.ok("Patient updated successfully", updatedPatient);
    }

    @DeleteMapping("hard/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePatient(@PathVariable UUID id) {

        patientService.deletePatient(id);


        return ResponseUtil.ok("Patient deleted successfully", null);

    }


}
