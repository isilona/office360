package io.office360.common.persistence.service;


import io.office360.common.interfaces.IDto;
import io.office360.common.persistence.model.INameableEntity;

public abstract class AbstractService<T extends INameableEntity, D extends IDto> extends AbstractOperationsService<T, D> implements IService<T> {

    public AbstractService() {
        super();
    }

    // API

}
