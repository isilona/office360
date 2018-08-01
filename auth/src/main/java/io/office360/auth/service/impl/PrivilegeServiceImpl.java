package io.office360.auth.service.impl;

import io.office360.auth.persistence.dao.IPrivilegeJpaDao;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.web.privilege.PrivilegeMapper;
import io.office360.auth.web.privilege.PrivilegeDto;
import io.office360.common.persistence.service.AbstractNameableService;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractNameableService<Privilege, PrivilegeDto> implements IPrivilegeService {

    private final IPrivilegeJpaDao dao;

    private final PrivilegeMapper mapper;

    @Autowired
    public PrivilegeServiceImpl(IPrivilegeJpaDao dao, PrivilegeMapper mapper) {
        super();
        this.dao = dao;
        this.mapper = mapper;
    }

    // API

    // find

    @Override
    public PrivilegeDto findByName(final String name) {
        Privilege found = dao.findByName(name);
        return mapper.entityToDto(found);
    }

    @Override
    protected IMapper getMapper() {
        return mapper;
    }

    // Spring

    @Override
    protected final IPrivilegeJpaDao getDao() {
        return dao;
    }

}
