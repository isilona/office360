package io.office360.auth.service.impl;

import com.google.common.base.Preconditions;
import io.office360.auth.persistence.dao.IAccountJpaDao;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.auth.web.account.AccountDto;
import io.office360.auth.web.account.AccountMapper;
import io.office360.common.persistence.service.AbstractOperationsService;
import io.office360.common.web.controller.data.mapping.IMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class AccountServiceImpl extends AbstractOperationsService<Account, AccountDto> implements IAccountService, UserDetailsService {

    private final IAccountJpaDao dao;

    private final AccountMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(IAccountJpaDao dao, AccountMapper mapper, PasswordEncoder passwordEncoder) {
        super();
        this.dao = dao;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    // API

    @Override
    public AccountDto create(final AccountDto dto) {
        Preconditions.checkNotNull(dto);

        // TODO : Check to do encoding in UI
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        Account toSave = mapper.dtoToEntity(dto);
        Account saved = getDao().save(toSave);

        return mapper.entityToDto(saved);
    }

    // find

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

        final AccountDto user = findByUsername(username);
        if (user != null) {
            return (Account) getMapper().dtoToEntity(user);
        } else {
            throw new UsernameNotFoundException("Username was not found: " + username);
        }
    }

    @Override
    public AccountDto findByUsername(String username) {
        Account found = dao.findByUsername(username);
        return mapper.entityToDto(found);
    }
}
