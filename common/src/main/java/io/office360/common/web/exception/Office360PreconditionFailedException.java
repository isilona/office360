package io.office360.common.web.exception;

// TODO: #4 Check usage
public final class Office360PreconditionFailedException extends RuntimeException {

    public Office360PreconditionFailedException() {
        super();
    }

    public Office360PreconditionFailedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public Office360PreconditionFailedException(final String message) {
        super(message);
    }

    public Office360PreconditionFailedException(final Throwable cause) {
        super(cause);
    }

}
