package io.office360.auth.service.impl;

import com.google.common.base.Preconditions;
import io.office360.auth.entity.Account;
import io.office360.auth.repository.IUserJpaDao;
import io.office360.auth.service.IUserService;
import io.office360.common.persistence.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl extends AbstractService<Account> implements IUserService, UserDetailsService {

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

//    @Override
//    @Transactional(readOnly = true)
//    public Account cd () {
////        final String userName = SpringSecurityUtil.getNameOfCurrentUser();
////        return getDao().findByName(userName);
//        return getDao().findByName(null);
//    }
//
    // Spring

    @Override
    protected final IUserJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Account> getSpecificationExecutor() {
        return dao;
    }

    /**
     * Loads the user from the datastore, by it's user name <br>
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
