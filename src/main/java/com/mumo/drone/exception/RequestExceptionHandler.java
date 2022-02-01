package com.mumo.drone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e){
        //Create Exception Details
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
         ApiException exception = new ApiException(
                e.getMessage(),
                 badRequest);

         //Return Response Entity
        return new ResponseEntity<>(exception, badRequest);
    }
}
