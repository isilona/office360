package io.office360.common.web.exception;

public final class Office360ResourceNotFoundException extends RuntimeException {

    public Office360ResourceNotFoundException() {
        super();
    }

    public Office360ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Office360ResourceNotFoundException(final String message) {
        super(message);
    }

    public Office360ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }

}