package io.office360.auth.service.impl;

import io.office360.auth.persistence.dao.IRoleJpaDao;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IRoleService;
import io.office360.common.persistence.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    @Autowired
    private IRoleJpaDao dao;

    public RoleServiceImpl() {
        super();
    }

    // API

    // get/find

    @Override
    public Role findByName(final String name) {
        return getDao().findByName(name);
    }

    // Spring

    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

}
