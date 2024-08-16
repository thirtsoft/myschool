package com.myschool.sn.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenActionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenActionException(String message) {
        super(message);
    }
}
