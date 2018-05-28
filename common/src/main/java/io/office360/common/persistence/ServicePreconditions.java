package io.office360.common.persistence;


import io.office360.common.persistence.exception.MyEntityNotFoundException;

public final class ServicePreconditions {

    private ServicePreconditions() {
        throw new IllegalStateException();
    }

    // API

    /**
     * Ensures that the entity reference passed as a parameter to the calling method is not null.
     *
     * @param entity an object reference
     * @return the non-null reference that was validated
     * @throws MyEntityNotFoundException if {@code entity} is null
     */
    public static <T> T checkEntityExists(final T entity) {
        if (entity == null) {
            throw new MyEntityNotFoundException();
        }
        return entity;
    }

}