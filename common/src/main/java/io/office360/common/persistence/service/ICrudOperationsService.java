package io.office360.common.persistence.service;

import io.office360.common.interfaces.IDto;

import java.util.List;

public interface ICrudOperationsService<D extends IDto> {

    // CREATE

    D create(final D resource);

    // READ

    D findOne(final long id);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<D> findAll();

    // UPDATE

    void update(final D resource);

    // DELETE

    void delete(final long id);

    void deleteAll();

    // COUNT

    long count();
}
