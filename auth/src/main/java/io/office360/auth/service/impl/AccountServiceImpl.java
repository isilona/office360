package io.office360.auth.service.impl;

import com.google.common.base.Preconditions;
import io.office360.auth.persistence.dao.IAccountJpaDao;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.auth.web.controller.data.mapping.AccountMapper;
import io.office360.auth.web.controller.data.response.AccountDto;
import io.office360.common.persistence.service.AbstractNameableService;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl extends AbstractNameableService<Account, AccountDto> implements IAccountService, UserDetailsService {

    private final IAccountJpaDao dao;

    private final AccountMapper mapper;

    @Autowired
    public AccountServiceImpl(IAccountJpaDao dao, AccountMapper mapper) {
        super();
        this.dao = dao;
        this.mapper = mapper;
    }

    // API

    // find

    @Override
    @Transactional(readOnly = true)
    public AccountDto findByName(final String name) {
        Account found = dao.findByName(name);
        return mapper.entityToDto(found);
    }

    // Spring

    @Override
    protected final IAccountJpaDao getDao() {
        return dao;
    }

    @Override
    protected IMapper getMapper() {
        return mapper;
    }

    /**
     * Loads the user from the dataStore, by it's user name <br>
     */
    @Override
    public final UserDetails loadUserByUsername(final String username) {
        Preconditions.checkNotNull(username);

        final AccountDto user = findByName(username);
        if (user != null) {
            return (Account) getMapper().dtoToEntity(user);
        } else {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }
    }
}
