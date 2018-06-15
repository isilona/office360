package io.office360.common.persistence.exception;

public final class Office360EntityNotFoundException extends RuntimeException {

    public Office360EntityNotFoundException() {
        super();
    }

    public Office360EntityNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Office360EntityNotFoundException(final String message) {
        super(message);
    }

    public Office360EntityNotFoundException(final Throwable cause) {
        super(cause);
    }

}
