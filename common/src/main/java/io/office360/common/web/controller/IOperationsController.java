package io.office360.common.web.controller;

import io.office360.common.interfaces.IDto;

public interface IOperationsController<D extends IDto> extends
        IPagingAndSortingOperationsController<D>,
        ICrudOperationsController<D> {
}
