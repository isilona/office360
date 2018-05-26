package io.office360.auth.service.main;

import io.office360.auth.spring.Office360ContextConfig;
import io.office360.auth.spring.Office360PersistenceJpaConfig;
import io.office360.auth.spring.Office360ServiceConfig;
import io.office360.common.persistence.model.INameableEntity;
import io.office360.common.service.AbstractServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration(classes = {
        Office360PersistenceJpaConfig.class,
        Office360ServiceConfig.class,
        Office360ContextConfig.class
}, loader = AnnotationConfigContextLoader.class)
public abstract class SecServiceIntegrationTest<T extends INameableEntity> extends AbstractServiceIntegrationTest<T> {

    //

}
