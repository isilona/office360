package io.office360.common.web.exception;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO {

    private final List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {
        super();
    }

    //

    public final void addFieldError(final String path, final String message) {
        final FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldErrors.add(error);
    }

    public final List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    //

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fieldErrors", fieldErrors)
                .toString();
    }
}
