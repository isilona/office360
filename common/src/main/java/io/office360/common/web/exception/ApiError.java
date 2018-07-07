package io.office360.common.web.exception;

import com.google.common.base.MoreObjects;

public class ApiError {

    private int status;
    private String message;
    private String developerMessage;

    public ApiError(final int status, final String message, final String developerMessage) {
        this.status = status;
        this.message = message;
        this.developerMessage = developerMessage;
    }

    //

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(final String developerMessage) {
        this.developerMessage = developerMessage;
    }

    //

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("message", message)
                .add("developerMessage", developerMessage)
                .toString();
    }
}