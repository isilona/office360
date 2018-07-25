package io.office360.common.persistence.service;

import io.office360.common.interfaces.IDto;

public interface IOperationsService<D extends IDto> extends
        IPagingAndSortingOperationsService<D>,
        ICrudOperationsService<D> {
}
