package io.office360.common.persistence.service;

import io.office360.common.interfaces.INameableDto;

public interface INameableService extends IOperationsService {

    //

    <D extends INameableDto> D findByName(String name);

}