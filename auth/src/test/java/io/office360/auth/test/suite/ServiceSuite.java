package io.office360.auth.test.suite;

import io.office360.auth.service.main.AccountServiceIntegrationTest;
import io.office360.auth.service.main.PrivilegeServiceIntegrationTest;
import io.office360.auth.service.main.RoleServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
        RoleServiceIntegrationTest.class,
        AccountServiceIntegrationTest.class,
        PrivilegeServiceIntegrationTest.class
})
public final class ServiceSuite {
    //
}
