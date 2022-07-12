package com.protasenya.cryptoCurrencyWatcher.exception;

import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends ApplicationException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
