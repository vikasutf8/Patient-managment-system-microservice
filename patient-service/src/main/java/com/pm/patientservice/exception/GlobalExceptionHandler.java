package com.pm.patientservice.exception;

import com.pm.patientservice.sharedDto.ApiResponse;
import com.pm.patientservice.utils.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseUtil.build(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Object>> handleAlreadyExists(AlreadyExistsException ex) {
        return ResponseUtil.build(ex.getMessage(), null, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex) {
        return ResponseUtil.build("Internal server error", null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
