package com.myschool.sn.referentiel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ReferentielException.class)
    public ResponseEntity<ErrorDto> referenceNotFound(ReferentielException exception) {
        return new ResponseEntity<>(ErrorDto.builder()
                .httpCode(NOT_FOUND.value())
                .message(exception.getMessage())
                .build(), NOT_FOUND);
    }
}