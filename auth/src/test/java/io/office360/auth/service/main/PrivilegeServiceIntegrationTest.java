//package io.office360.auth.service.main;
//
//import io.office360.auth.persistence.entity.Privilege;
//import io.office360.auth.service.IPrivilegeService;
//import io.office360.common.persistence.service.INameableService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//
//import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
//
//public class PrivilegeServiceIntegrationTest extends SecServiceIntegrationTest<Privilege> {
//
//    @Autowired
//    private IPrivilegeService privilegeService;
//
//    // create
//
//    @Test
//    public void whenSaveIsPerformed_thenNoException() {
//        privilegeService.create(createNewEntity());
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void whenAUniqueConstraintIsBroken_thenSpringSpecificExceptionIsThrown() {
//        final String name = randomAlphabetic(8);
//
//        privilegeService.create(createNewEntity(name));
//        privilegeService.create(createNewEntity(name));
//    }
//
//    // template method
//
//    @Override
//    protected final INameableService<Privilege> getApi() {
//        return privilegeService;
//    }
//
//    @Override
//    protected final Privilege createNewEntity() {
//        return new Privilege(randomAlphabetic(8));
//    }
//
//    // util
//
//    protected final Privilege createNewEntity(final String name) {
//        return new Privilege(name);
//    }
//
//    @Override
//    protected void invalidate(final Privilege entity) {
//        entity.setName(null);
//    }
//
//    @Override
//    protected void change(final Privilege entity) {
//        entity.setName(randomAlphabetic(6));
//    }
//
//}
//
