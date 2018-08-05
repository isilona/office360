package io.office360.auth.service.main;

import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.web.privilege.PrivilegeMapper;
import io.office360.auth.web.privilege.PrivilegeDto;
import io.office360.common.persistence.service.INameableService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class PrivilegeServiceIntegrationTest extends SecServiceIntegrationTest<PrivilegeDto> {

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        privilegeService.create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        privilegeService.create(createNewEntity(name));
        privilegeService.create(createNewEntity(name));
    }

    // template method

    @Override
    protected final INameableService getApi() {
        return privilegeService;
    }

    @Override
    protected final PrivilegeDto createNewEntity() {
        Privilege entity = new Privilege(randomAlphabetic(8));
        return privilegeMapper.entityToDto(entity);
    }

    // util

    protected final PrivilegeDto createNewEntity(final String name) {
        Privilege entity = new Privilege(name);
        return privilegeMapper.entityToDto(entity);
    }

    @Override
    protected void invalidate(final PrivilegeDto entity) {
        entity.setName(null);
    }

    @Override
    protected void change(final PrivilegeDto entity) {
        entity.setName(randomAlphabetic(6));
    }

}

