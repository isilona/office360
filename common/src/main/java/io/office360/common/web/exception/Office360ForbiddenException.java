package io.office360.common.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when user is forbidden to execute specified operation or access specified data.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class Office360ForbiddenException extends RuntimeException {

    public Office360ForbiddenException() {
        super();
    }

    public Office360ForbiddenException(final String message) {
        super(message);
    }

    public Office360ForbiddenException(final Throwable cause) {
        super(cause);
    }

}
