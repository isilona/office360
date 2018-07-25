//package io.office360.common.integration.service;
//
//import io.office360.common.persistence.model.INameableEntity;
//import io.office360.common.persistence.service.INameableService;
//import io.office360.common.util.SearchField;
//import io.office360.common.util.order.OrderByName;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Sort;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.junit.Assert.*;
//
//public abstract class AbstractServiceIntegrationTest<T extends INameableEntity> extends AbstractRawServiceIntegrationTest<T> {
//
//    // tests
//
//    // find - one - by name
//
//    @Test
//    /**/ public final void givenResourceExists_whenResourceIsRetrievedByName_thenNoExceptions() {
//        // Given
//        final T existingResource = getApi().create(createNewEntity());
//
//        // When
//        getApi().findByName(existingResource.getName());
//    }
//
//    @Test
//    /**/ public final void givenResourceExists_whenResourceIsRetrievedByName_thenResourceIsFound() {
//        // Given
//        final T existingResource = getApi().create(createNewEntity());
//
//        // When
//        final T resourceByName = getApi().findByName(existingResource.getName());
//
//        // Then
//        assertNotNull(resourceByName);
//    }
//
//    @Test
//    /**/ public final void givenResourceExists_whenResourceIsRetrievedByName_thenFoundResourceIsCorrect() {
//        // Given
//        final T existingResource = getApi().create(createNewEntity());
//        // When
//        final T resourceByName = getApi().findByName(existingResource.getName());
//
//        // Then
//        assertThat(existingResource, equalTo(resourceByName));
//    }
//
//    @Test
//    @Ignore
//    /**/ public final void givenExistingResourceHasSpaceInName_whenResourceIsRetrievedByName_thenFoundResourceIsCorrect() {
//        final T newEntity = createNewEntity();
//        // / newEntity.setName(randomAlphabetic(4) + " " + randomAlphabetic(4));
//
//        // Given
//        final T existingResource = getApi().create(newEntity);
//
//        // When
//        final T resourceByName = getApi().findByName(existingResource.getName());
//
//        // Then
//        assertThat(existingResource, equalTo(resourceByName));
//    }
//
//    // find - all - sorting
//
//    @Test
//    @Ignore("order has a temporary problem")
//    /**/ public final void whenResourcesAreRetrievedSorted_thenResourcesAreIndeedOrdered() {
//        persistNewEntity();
//        persistNewEntity();
//
//        // When
//        final List<T> resourcesSorted = getApi().findAllSorted(SearchField.name.name(), Sort.Direction.ASC.name());
//
//        // Then
//        assertTrue(new OrderByName<T>().isOrdered(resourcesSorted));
//    }
//
//    // find - all - pagination and sorting
//
//    @Test
//    /**/ public final void whenResourcesAreRetrievedPaginatedAndSorted_thenNoExceptions() {
//        getApi().findAllPaginatedAndSorted(0, 41, SearchField.name.name(), Sort.Direction.DESC.name());
//    }
//
//    @Test
//    @Ignore("order has a temporary problem")
//    /**/ public final void whenResourcesAreRetrievedPaginatedAndSorted_thenResourcesAreIndeedOrdered() {
//        persistNewEntity();
//        persistNewEntity();
//
//        // When
//        final Page<T> resourcesPaginatedAndSorted = getApi().findAllPaginatedAndSorted(0, 4, SearchField.name.name(), Sort.Direction.ASC.name());
//
//        // Then
//        assertTrue(new OrderByName<T>().isOrdered(resourcesPaginatedAndSorted));
//    }
//
//    // template method
//
//    @Override
//    protected abstract INameableService<T> getApi();
//
//}