package io.office360.auth.service.main;

import io.office360.auth.config.Office360AuthSecurityConfig;
import io.office360.auth.spring.Office360AuthContextConfig;
import io.office360.auth.spring.Office360AuthPersistenceJpaConfig;
import io.office360.auth.spring.Office360AuthServiceConfig;
import io.office360.common.integration.service.AbstractServiceIntegrationTest;
import io.office360.common.interfaces.INameableDto;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {
        Office360AuthPersistenceJpaConfig.class,
        Office360AuthServiceConfig.class,
        Office360AuthContextConfig.class,
        Office360AuthSecurityConfig.class
}, loader = AnnotationConfigContextLoader.class)
public abstract class SecServiceIntegrationTest<T extends INameableDto> extends AbstractServiceIntegrationTest<T> {

    //

}
