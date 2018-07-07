package io.office360.auth.service.impl;

import io.office360.auth.persistence.dao.IPrivilegeJpaDao;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.service.IPrivilegeService;
import io.office360.common.persistence.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PrivilegeServiceImpl extends AbstractService<Privilege> implements IPrivilegeService {

    private final IPrivilegeJpaDao dao;

    @Autowired
    public PrivilegeServiceImpl(IPrivilegeJpaDao dao) {
        super();
        this.dao = dao;
    }

    // API

    // find

    @Override
    public Privilege findByName(final String name) {
        return getDao().findByName(name);
    }

    // Spring

    @Override
    protected final IPrivilegeJpaDao getDao() {
        return dao;
    }

}
