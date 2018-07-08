package io.office360.common.interfaces.operations;

import java.io.Serializable;
import java.util.List;

public interface ICrudOperations<T extends Serializable> {

    // CREATE

    T create(final T resource);

    // READ

    T findOne(final long id);

    /**
     * - contract: if nothing is found, an empty list will be returned to the calling client <br>
     */
    List<T> findAll();

    // UPDATE

    void update(final T resource);

    // DELETE

    void delete(final long id);

    void deleteAll();

    // COUNT

    long count();
}
