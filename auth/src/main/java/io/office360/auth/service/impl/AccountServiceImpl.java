package io.office360.auth.service.impl;

import com.google.common.base.Preconditions;
import io.office360.auth.persistence.dao.IAccountJpaDao;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.auth.web.account.AccountDto;
import io.office360.auth.web.account.AccountMapper;
import io.office360.auth.web.account.AccountRegisterDto;
import io.office360.auth.web.account.AccountRegisterMapper;
import io.office360.common.interfaces.IDto;
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
public class AccountServiceImpl extends AbstractOperationsService implements IAccountService, UserDetailsService {

    private final IAccountJpaDao dao;

    private final AccountMapper mapper;

    private final AccountRegisterMapper registerMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(IAccountJpaDao dao,
                              AccountMapper mapper,
                              AccountRegisterMapper registerMapper,
                              PasswordEncoder passwordEncoder) {
        super();
        this.dao = dao;
        this.mapper = mapper;
        this.registerMapper = registerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // API

    @Override
    public <D extends IDto> D create(D dto) {
        AccountRegisterDto casted = (AccountRegisterDto) dto;

        Preconditions.checkNotNull(casted);

        // TODO : Check to do encoding in UI then use the default create method
        String encodedPassword = passwordEncoder.encode(casted.getPassword());
        casted.setPassword(encodedPassword);

        Account toSave = registerMapper.dtoToEntity(casted);
        Account saved = getDao().save(toSave);
        return (D) registerMapper.entityToDto(saved);
    }

    // find

    // Spring

    @Override
    protected final IAccountJpaDao getDao() {
        return dao;
    }

    @Override
    protected IMapper<AccountDto, Account> getMapper() {
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
            return getMapper().dtoToEntity(user);
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
