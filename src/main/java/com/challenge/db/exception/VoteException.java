package com.challenge.db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VoteException extends Exception {
    public VoteException(String message) {
        super(message);
    }
}
