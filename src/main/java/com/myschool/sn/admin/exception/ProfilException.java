package com.myschool.sn.admin.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfilException extends RuntimeException {

    public ProfilException(String message) {
        super(message);
    }
}
