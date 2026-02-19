package com.pm.patientservice.sharedDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class ApiResponse <T>{
    private String message;
//    private int status;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse() {

    }
}
