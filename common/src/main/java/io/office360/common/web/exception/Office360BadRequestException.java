package io.office360.common.web.exception;

public final class Office360BadRequestException extends RuntimeException {

    public Office360BadRequestException() {
        super();
    }

    public Office360BadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Office360BadRequestException(final String message) {
        super(message);
    }

    public Office360BadRequestException(final Throwable cause) {
        super(cause);
    }

}
