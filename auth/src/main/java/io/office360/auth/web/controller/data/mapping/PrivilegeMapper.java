package io.office360.auth.web.controller.data.mapping;

import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.web.controller.data.response.PrivilegeDto;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper extends IMapper<PrivilegeDto, Privilege> {

    PrivilegeDto entityToDto(Privilege entity);

    Privilege dtoToEntity(PrivilegeDto dto);

}
