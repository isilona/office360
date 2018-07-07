package io.office360.common.unit.web;

import io.office360.common.interfaces.INameableDto;
import io.office360.common.persistence.model.INameableEntity;
import io.office360.common.web.IUriMapper;
import io.office360.common.web.UriMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UriMapper.class})
public class UriMapperUnitTest {

    @Autowired
    private IUriMapper uriMapper;

    @Test
    public final void whenEntityClassIsPassed_thenItsURIBaseIsReturned() {

        // When
        final Class testedEntity = TestedEntity.class;

        // Then
        assertTrue(uriMapper.getUriBase(testedEntity).equals("testedentitys"));
    }

    class TestedEntity implements INameableEntity, INameableDto {

        @Override
        public Long getId() {
            return null;
        }

        @Override
        public void setId(Long id) {

        }

        @Override
        public String getName() {
            return null;
        }
    }

}
