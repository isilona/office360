package io.office360.auth.service.impl;

import com.google.common.base.Preconditions;
import io.office360.auth.persistence.dao.IAccountJpaDao;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.common.persistence.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl extends AbstractService<Account> implements IAccountService, UserDetailsService {

    private final IAccountJpaDao dao;

    @Autowired
    public AccountServiceImpl(IAccountJpaDao dao) {
        super();
        this.dao = dao;
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public Account findByName(final String name) {
        return dao.findByName(name);
    }

    // Spring

    @Override
    protected final IAccountJpaDao getDao() {
        return dao;
    }

    /**
     * Loads the user from the dataStore, by it's user name <br>
     */
    @Override
    public final UserDetails loadUserByUsername(final String username) {
        Preconditions.checkNotNull(username);

        final Account user = findByName(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }
    }
}
