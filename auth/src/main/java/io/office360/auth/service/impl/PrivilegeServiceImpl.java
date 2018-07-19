package io.office360.auth.service.impl;

import io.office360.auth.persistence.dao.IPrivilegeJpaDao;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.web.controller.data.mapping.PrivilegeMapper;
import io.office360.auth.web.controller.data.response.PrivilegeDto;
import io.office360.common.persistence.service.AbstractService;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService<Privilege, PrivilegeDto> implements IPrivilegeService {

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
    public Privilege findByName(final String name) {
        return getDao().findByName(name);
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
