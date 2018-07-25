package io.office360.common.persistence.service;

import io.office360.common.interfaces.INameableDto;

public interface INameableService<D extends INameableDto> extends IOperationsService<D> {

    //

    D findByName(String name);

}