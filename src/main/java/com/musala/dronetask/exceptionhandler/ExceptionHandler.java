package com.musala.dronetask.exceptionhandler;

import java.io.IOException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.enums.ResponseCode;
import com.musala.dronetask.enums.ResponseStatus;
import com.musala.dronetask.exceptions.CustomException;


@RestControllerAdvice
public class ExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass()); // logger

    
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<GenericResponse<?>> onConstraintValidationException(ConstraintViolationException ex) throws IOException {

        GenericResponse<?> response = new GenericResponse<>();
        
		for (ConstraintViolation<?> constraintViolation : ex.getConstraintViolations()) {
			log.error(constraintViolation.getMessage());
	        response.setResponseCode("400");
	        response.setResponseDescription(constraintViolation.getMessage());
	        response.setResponseStatus(ResponseStatus.FAILURE);

		}
        
        return ResponseEntity.badRequest().body(response);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<GenericResponse<?>> onMethodArgumentNotValidException(MethodArgumentNotValidException ex) throws IOException {

        GenericResponse<?> response = new GenericResponse<>();
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			log.error(fieldError.getDefaultMessage());
	        response.setResponseCode("400");
	        response.setResponseDescription(fieldError.getDefaultMessage());
	        response.setResponseStatus(ResponseStatus.FAILURE);

		}
        
        return ResponseEntity.badRequest().body(response);
    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<GenericResponse<?>> onNotFoundException(NotFoundException ex) throws IOException {

        log.error(ex.getMessage());
        
        GenericResponse<?> response = new GenericResponse<>();
        response.setResponseCode("404");
        response.setResponseDescription(ex.getMessage());
        response.setResponseStatus(ResponseStatus.FAILURE);

        return ResponseEntity.badRequest().body(response);
    }
    

    @org.springframework.web.bind.annotation.ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<GenericResponse<?>> onNotFoundException(DataIntegrityViolationException ex) throws IOException {

        log.error(ex.getMessage());
        
        GenericResponse<?> response = new GenericResponse<>();
        response.setResponseCode("400");
        response.setResponseDescription(ex.getMostSpecificCause().getMessage());
        response.setResponseStatus(ResponseStatus.FAILURE);

        return ResponseEntity.badRequest().body(response);
    }
    
    
    @org.springframework.web.bind.annotation.ExceptionHandler({CustomException.class})
    public ResponseEntity<GenericResponse<?>> genericException(CustomException ex) throws IOException {

        log.error(ex.getMessage());

        GenericResponse<?> response = new GenericResponse<>();
        response.setResponseStatus(ResponseStatus.FAILURE);
        response.setResponseCode(ResponseCode.FAILURE.getCode());
        response.setResponseDescription(ex.getMessage());

        return ResponseEntity.badRequest().body(response);
    }
    
}