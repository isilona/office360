package io.office360.common.persistence.service;


import io.office360.common.persistence.model.INameableEntity;

public abstract class AbstractService<T extends INameableEntity> extends AbstractOperationsService<T> implements IService<T> {

    public AbstractService() {
        super();
    }

    // API

}
