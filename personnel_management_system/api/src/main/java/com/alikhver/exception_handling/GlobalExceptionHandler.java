package com.alikhver.exception_handling;

import com.alikhver.response.BadResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity handleException(Exception e) {
        BadResponse badResponse = new BadResponse();

        badResponse.setInfo(e.getMessage());
        badResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity(badResponse, HttpStatus.NOT_FOUND);
    }

}
