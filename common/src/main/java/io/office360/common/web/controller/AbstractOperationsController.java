package io.office360.common.web.controller;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.office360.common.persistence.model.IEntity;
import io.office360.common.persistence.service.IOperationsService;
import io.office360.common.web.RestPreconditions;
import io.office360.common.web.events.AfterResourceCreatedEvent;
import io.office360.common.web.events.MultipleResourcesRetrievedEvent;
import io.office360.common.web.events.PaginatedResultsRetrievedEvent;
import io.office360.common.web.events.SingleResourceRetrievedEvent;
import io.office360.common.web.exception.Office360ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public abstract class AbstractOperationsController<T extends IEntity> implements IOperationsController<T> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<T> clazz;

    @Autowired
    protected ApplicationEventPublisher eventPublisher;

    public AbstractOperationsController(final Class<T> clazzToSet) {
        super();

        Preconditions.checkNotNull(clazzToSet);
        clazz = clazzToSet;
    }

    // API - CRUD

    // CREATE

    public final void createInternal(final T resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        final T existingResource = getService().create(resource);

        // - note: mind the autoboxing and potential NPE when the resource has null id at this point (likely when working with DTOs)
        eventPublisher.publishEvent(new AfterResourceCreatedEvent<>(clazz, uriBuilder, response, existingResource.getId().toString()));
    }

    // READ

    public final T findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final T resource = findOneInternal(id);
        eventPublisher.publishEvent(new SingleResourceRetrievedEvent<>(clazz, uriBuilder, response));
        return resource;
    }

    protected final T findOneInternal(final Long id) {
        return RestPreconditions.checkNotNull(getService().findOne(id));
    }

    public final List<T> findAllInternal(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        if (request.getParameterNames().hasMoreElements()) {
            throw new Office360ResourceNotFoundException();
        }

        eventPublisher.publishEvent(new MultipleResourcesRetrievedEvent<>(clazz, uriBuilder, response));
        return getService().findAll();
    }

    // UPDATE

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    public final void updateInternal(final long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(resource);
    }

    // DELETE

    public final void deleteInternal(final long id) {
        // InvalidDataAccessApiUsageException - ResourceNotFoundException
        // IllegalStateException - ResourceNotFoundException
        // DataAccessException - ignored
        getService().delete(id);
    }

    // COUNT

    public final long countInternal() {
        // InvalidDataAccessApiUsageException dataEx - ResourceNotFoundException
        return getService().count();
    }

    // API - PAGING & SORTING

    public final List<T> findPaginatedInternal(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<T> resultPage = getService().findAllPaginated(page, size);
        if (page > resultPage.getTotalPages()) {
            throw new Office360ResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return Lists.newArrayList(resultPage.getContent());
    }

    public final List<T> findSortedInternal(final String sortBy, final String sortOrder) {
        return getService().findAllSorted(sortBy, sortOrder);
    }

    public final List<T> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        final Page<T> resultPage = getService().findAllPaginatedAndSorted(page, size, sortBy, sortOrder);
        if (page > resultPage.getTotalPages()) {
            throw new Office360ResourceNotFoundException();
        }
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(clazz, uriBuilder, response, page, resultPage.getTotalPages(), size));

        return Lists.newArrayList(resultPage.getContent());
    }

    // template method

    protected abstract IOperationsService<T> getService();

}
