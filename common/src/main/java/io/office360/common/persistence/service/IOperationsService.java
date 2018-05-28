package io.office360.common.persistence.service;

import io.office360.common.interfaces.IOperations;
import io.office360.common.persistence.model.IEntity;


public interface IOperationsService<T extends IEntity> extends IOperations<T> {

}
