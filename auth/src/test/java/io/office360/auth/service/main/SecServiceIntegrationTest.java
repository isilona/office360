package io.office360.auth.service.main;

import io.office360.auth.spring.Office360AuthContextConfig;
import io.office360.auth.spring.Office360AuthPersistenceJpaConfig;
import io.office360.auth.spring.Office360AuthServiceConfig;
import io.office360.common.integration.service.AbstractServiceIntegrationTest;
import io.office360.common.persistence.model.INameableEntity;
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
        Office360AuthContextConfig.class
}, loader = AnnotationConfigContextLoader.class)
public abstract class SecServiceIntegrationTest<T extends INameableEntity> extends AbstractServiceIntegrationTest<T> {

    //

}
