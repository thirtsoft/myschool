package com.myschool.sn.referentiel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReferentielException extends RuntimeException {

    public ReferentielException(String message) {
        super(message);
    }
}
