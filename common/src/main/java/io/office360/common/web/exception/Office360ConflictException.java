package io.office360.common.web.exception;

public final class Office360ConflictException extends RuntimeException {

    public Office360ConflictException() {
        super();
    }

    public Office360ConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Office360ConflictException(final String message) {
        super(message);
    }

    public Office360ConflictException(final Throwable cause) {
        super(cause);
    }

}