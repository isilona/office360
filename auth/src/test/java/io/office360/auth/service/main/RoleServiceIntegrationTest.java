package io.office360.auth.service.main;

import com.google.common.collect.Sets;
import io.office360.auth.persistence.entity.Privilege;
import io.office360.auth.persistence.entity.Role;
import io.office360.auth.service.IAccountService;
import io.office360.auth.service.IPrivilegeService;
import io.office360.auth.service.IRoleService;
import io.office360.auth.web.privilege.PrivilegeMapper;
import io.office360.auth.web.role.RoleMapper;
import io.office360.auth.web.privilege.PrivilegeDto;
import io.office360.auth.web.role.RoleDto;
import io.office360.common.persistence.service.INameableService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

@Ignore
public class RoleServiceIntegrationTest extends SecServiceIntegrationTest<RoleDto> {

    @Autowired
    private IPrivilegeService privilegeService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IAccountService userService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    // create

    @Test
    public void whenSaveIsPerformed_thenNoException() {
        roleService.create(createNewEntity());
    }

    @Test(expected = DataAccessException.class)
    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
        final String name = randomAlphabetic(8);

        roleService.create(createNewEntity(name));
        roleService.create(createNewEntity(name));
    }

    // scenario

    /**
     * - known issue: this fails on a H2 database
     */
    @Test
    @Ignore
    public final void givenEntityExistsWithAssociationScenarios_whenDeletingEverything_thenNoException() {
        final PrivilegeDto existingAssociation =
                getAssociationService().create(privilegeMapper.entityToDto(new Privilege(randomAlphabetic(6))));
        final RoleDto newResource = createNewEntity();
        newResource.getPrivileges().add(existingAssociation);
        getApi().create(newResource);

        userService.deleteAll();
        roleService.deleteAll();
        // privilegeService.deleteAll();
    }

    @Test
    public final void whenCreatingNewResourceWithExistingAssociations_thenNewResourceIsCorrectlyCreated() {
        final PrivilegeDto existingAssociation =
                getAssociationService().create(privilegeMapper.entityToDto(new Privilege(randomAlphabetic(6))));
        final RoleDto newResource = createNewEntity();
        newResource.getPrivileges().add(existingAssociation);
        getApi().create(newResource);

        final RoleDto newResource2 = createNewEntity();
        newResource2.getPrivileges().add(existingAssociation);
        getApi().create(newResource2);
    }

    @Test
    public final void whenScenarioOfWorkingWithAssociations_thenTheChangesAreCorrectlyPersisted() {
        final PrivilegeDto existingAssociation =
                getAssociationService().create(privilegeMapper.entityToDto(new Privilege(randomAlphabetic(6))));
        final RoleDto resource1 =
                roleMapper.entityToDto(new Role(randomAlphabetic(6), Sets.newHashSet(privilegeMapper.dtoToEntity(existingAssociation))));

        final RoleDto resource1ViewOfServerBefore = getApi().create(resource1);
        assertThat(resource1ViewOfServerBefore.getPrivileges(), hasItem(existingAssociation));

        final RoleDto resource2 =
                roleMapper.entityToDto(new Role(randomAlphabetic(6), Sets.newHashSet(privilegeMapper.dtoToEntity(existingAssociation))));
        getApi().create(resource2);

        final RoleDto resource1ViewOfServerAfter = getApi().findOne(resource1ViewOfServerBefore.getId());
        assertThat(resource1ViewOfServerAfter.getPrivileges(), hasItem(existingAssociation));
    }

    // template method

    @Override
    protected final INameableService<RoleDto> getApi() {
        return roleService;
    }

    @Override
    protected final RoleDto createNewEntity() {
        return createNewEntity(randomAlphabetic(8));
    }

    @Override
    protected void invalidate(final RoleDto entity) {
        entity.setName(null);
    }

    @Override
    protected void change(final RoleDto entity) {
        entity.setName(randomAlphabetic(6));
    }

    final IPrivilegeService getAssociationService() {
        return privilegeService;
    }

    protected final RoleDto createNewEntity(final String name) {
        Role entity = new Role(name, Sets.newHashSet());
        return roleMapper.entityToDto(entity);
    }

}
