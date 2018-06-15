package io.office360.common.web;

import io.office360.common.web.exception.Office360BadRequestException;
import io.office360.common.web.exception.Office360ConflictException;
import io.office360.common.web.exception.Office360ResourceNotFoundException;
import org.springframework.http.HttpStatus;

/**
 * Simple static methods to be called at the start of your own methods to verify
 * correct arguments and state. If the Precondition fails, an {@link HttpStatus}
 * code is thrown
 */
public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws Office360ResourceNotFoundException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference) {
        return checkNotNull(reference, null);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param message   the message of the exception if the check fails
     * @return the non-null reference that was validated
     * @throws Office360ResourceNotFoundException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, final String message) {
        if (reference == null) {
            throw new Office360ResourceNotFoundException(message);
        }
        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling
     * method is not null.
     *
     * @param reference an object reference
     * @return the non-null reference that was validated
     * @throws Office360BadRequestException if {@code reference} is null
     */
    public static <T> T checkRequestElementNotNull(final T reference) {
        return checkRequestElementNotNull(reference, null);
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param message   the message of the exception if the check fails
     * @return the non-null reference that was validated
     * @throws Office360BadRequestException if {@code reference} is null
     */
    public static <T> T checkRequestElementNotNull(final T reference, final String message) {
        if (reference == null) {
            throw new Office360BadRequestException(message);
        }
        return reference;
    }

    /**
     * Ensures the truth of an expression
     *
     * @param expression a boolean expression
     * @throws Office360ConflictException if {@code expression} is false
     */
    public static void checkRequestState(final boolean expression) {
        checkRequestState(expression, null);
    }

    /**
     * Ensures the truth of an expression
     *
     * @param expression a boolean expression
     * @param message    the message of the exception if the check fails
     * @throws Office360ConflictException if {@code expression} is false
     */
    public static void checkRequestState(final boolean expression, final String message) {
        if (!expression) {
            throw new Office360ConflictException(message);
        }
    }

}
