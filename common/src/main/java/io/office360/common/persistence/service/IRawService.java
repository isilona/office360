package io.office360.common.persistence.service;

import io.office360.common.interfaces.IOperations;
import io.office360.common.persistence.model.IEntity;


public interface IRawService<T extends IEntity> extends IOperations<T> {

//    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);

//    Page<T> findAllPaginatedRaw(final int page, final int size);

}
