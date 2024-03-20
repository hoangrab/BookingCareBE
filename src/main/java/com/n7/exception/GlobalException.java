package com.n7.exception;

import com.n7.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().size() > 0 ?
                ex.getBindingResult().getAllErrors().get(0).toString() : "Unknown error, please check input";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse<>(errorMessage));
    }
}
