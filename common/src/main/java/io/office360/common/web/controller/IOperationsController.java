package io.office360.common.web.controller;

import io.office360.common.interfaces.operations.IOperations;
import io.office360.common.persistence.model.IEntity;

public interface IOperationsController<T extends IEntity> extends IOperations<T> {
}
