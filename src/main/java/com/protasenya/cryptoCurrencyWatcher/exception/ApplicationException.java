package com.protasenya.cryptoCurrencyWatcher.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {

    public ApplicationException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();

}
