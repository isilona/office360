package io.office360.auth.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        LiveSuite.class,
        ServiceSuite.class,
        ControllerSuite.class
})
public final class AllSuite {
    //
}
