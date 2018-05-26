package io.office360.auth.service.main;

import io.office360.common.persistence.model.INameableEntity;
import io.office360.common.service.AbstractServiceIntegrationTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application.properties")
public abstract class SecServiceIntegrationTest<T extends INameableEntity> extends AbstractServiceIntegrationTest<T> {

    //

}
