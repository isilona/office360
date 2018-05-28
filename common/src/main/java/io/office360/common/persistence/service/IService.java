package io.office360.common.persistence.service;

import io.office360.common.interfaces.IByNameApi;
import io.office360.common.persistence.model.INameableEntity;

public interface IService<T extends INameableEntity> extends IOperationsService<T>, IByNameApi<T> {

    //

}