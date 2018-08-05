package io.office360.auth.service.main;

import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.auth.web.account.AccountDto;
import io.office360.auth.web.account.AccountMapper;
import io.office360.common.persistence.service.INameableService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Ignore
public class AccountServiceIntegrationTest extends SecServiceIntegrationTest<AccountDto> {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        getApi().create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        getApi().create(createNewEntity(name));
        getApi().create(createNewEntity(name));
    }

    // template method

    @Override
    protected final INameableService getApi() {
//        return accountService;
        return null;
    }

    @Override
    protected final AccountDto createNewEntity() {
        Account entity = new Account.Builder(randomAlphabetic(8), randomAlphabetic(8))
                .setRoles(Sets.newHashSet()).build();
        return accountMapper.entityToDto(entity);
    }

    protected final AccountDto createNewEntity(final String name) {
        Account entity = new Account.Builder(name, randomAlphabetic(8))
                .setRoles(Sets.newHashSet()).build();
        return accountMapper.entityToDto(entity);
    }

    @Override
    protected void invalidate(final AccountDto entity) {
        entity.setName(null);
    }

    @Override
    protected void change(final AccountDto entity) {
        entity.setName(randomAlphabetic(6));
    }

}
