package io.office360.restapi.service.impl;

import io.office360.common.persistence.service.AbstractService;
import io.office360.restapi.persistence.dao.IUserJpaDao;
import io.office360.restapi.persistence.model.Account;
import io.office360.restapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl extends AbstractService<Account> implements IUserService {

    @Autowired
    private IUserJpaDao dao;

    public UserServiceImpl() {
        super();
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public Account findByName(final String name) {
        return dao.findByName(name);
    }

    // other

    // Spring

    @Override
    protected final IUserJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Account> getSpecificationExecutor() {
        return dao;
    }
}
