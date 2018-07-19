package io.office360.common.web.controller.data.mapping;

import io.office360.common.interfaces.IDto;
import io.office360.common.persistence.model.IEntity;

public interface IMapper<D extends IDto, E extends IEntity> {

    D entityToDto(E entity);

    E dtoToEntity(D dto);
}
