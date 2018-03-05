package io.office360.common;


import io.office360.common.persistence.model.IEntity;

public interface IUriMapper {

    <T extends IEntity> String getUriBase(final Class<T> clazz);

}
