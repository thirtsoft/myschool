package com.myschool.sn.parent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParentException extends RuntimeException {

    public ParentException(String message) {
        super(message);
    }
}
