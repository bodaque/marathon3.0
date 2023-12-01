package com.epam.marathon.auto.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpamersTAException extends RuntimeException {

    public UpamersTAException() {
    }

    public UpamersTAException(String message) {
        super(message);
        log.error(message);
    }

    public UpamersTAException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}
