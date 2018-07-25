package io.office360.common.persistence.service;


import io.office360.common.interfaces.INameableDto;
import io.office360.common.persistence.model.INameableEntity;

public abstract class AbstractNameableService<T extends INameableEntity, D extends INameableDto>
        extends AbstractOperationsService<T, D>
        implements INameableService<D> {

    public AbstractNameableService() {
        super();
    }

    // API

}
