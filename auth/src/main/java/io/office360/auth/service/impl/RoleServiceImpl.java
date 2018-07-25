package io.office360.auth.service.impl;

import io.office360.auth.persistence.dao.IRoleJpaDao;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IRoleService;
import io.office360.auth.web.controller.data.mapping.RoleMapper;
import io.office360.auth.web.controller.data.response.RoleDto;
import io.office360.common.persistence.service.AbstractNameableService;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends AbstractNameableService<Role, RoleDto> implements IRoleService {

    private final IRoleJpaDao dao;

    private final RoleMapper mapper;

    @Autowired
    public RoleServiceImpl(IRoleJpaDao dao, RoleMapper mapper) {
        super();
        this.dao = dao;
        this.mapper = mapper;
    }

    // API

    // get/find

    @Override
    public RoleDto findByName(final String name) {
        Role found = dao.findByName(name);
        return mapper.entityToDto(found);
    }

    @Override
    protected IMapper getMapper() {
        return mapper;
    }
    // Spring

    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

}
