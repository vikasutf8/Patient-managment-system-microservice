package com.pm.patientservice.utils;

import com.pm.patientservice.sharedDto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> build(
            String message,
            T data,
            HttpStatus status
    ) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .message(message)
                .data(data)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return build(message, data, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return build(message, data, HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent(String message) {
        return build(message, null, HttpStatus.NO_CONTENT);
    }
}

