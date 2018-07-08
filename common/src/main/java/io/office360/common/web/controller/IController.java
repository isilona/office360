package io.office360.common.web.controller;

import io.office360.common.interfaces.IByNameApi;
import io.office360.common.persistence.model.INameableEntity;

public interface IController<T extends INameableEntity> extends IOperationsController<T>, IByNameApi<T> {
}
