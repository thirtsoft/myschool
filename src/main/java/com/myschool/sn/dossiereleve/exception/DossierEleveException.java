package com.myschool.sn.dossiereleve.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DossierEleveException extends RuntimeException {

    public DossierEleveException(String message) {
        super(message);
    }
}
