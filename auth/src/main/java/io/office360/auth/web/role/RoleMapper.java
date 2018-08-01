package io.office360.auth.web.role;

import io.office360.auth.persistence.entity.Role;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends IMapper<RoleDto, Role> {

    RoleDto entityToDto(Role entity);

    Role dtoToEntity(RoleDto dto);

}
