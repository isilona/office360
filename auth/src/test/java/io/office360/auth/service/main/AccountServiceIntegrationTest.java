package io.office360.auth.service.main;

import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Account;
import io.office360.auth.service.IAccountService;
import io.office360.common.persistence.service.IService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Ignore
public class AccountServiceIntegrationTest extends SecServiceIntegrationTest<Account> {

    @Autowired
    private IAccountService accountService;

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
    protected final IService<Account> getApi() {
        return accountService;
    }

    @Override
    protected final Account createNewEntity() {
        return new Account(randomAlphabetic(8), randomAlphabetic(8), Sets.newHashSet());
    }

    protected final Account createNewEntity(final String name) {
        return new Account(name, randomAlphabetic(8), Sets.newHashSet());
    }

    @Override
    protected void invalidate(final Account entity) {
        entity.setName(null);
    }

    @Override
    protected void change(final Account entity) {
        entity.setName(randomAlphabetic(6));
    }

}
