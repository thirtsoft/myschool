package com.myschool.sn.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UtilsException extends RuntimeException {

    public UtilsException(String message) {
        super(message);
    }
}
