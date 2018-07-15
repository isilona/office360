package io.office360.common.web.controller;

import io.office360.common.persistence.model.IEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ICrudOperationsController<T extends IEntity> {


    // CREATE

    void createInternal(final T resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    // READ

    T findOneInternal(final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    List<T> findAllInternal(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    // UPDATE

    void updateInternal(final long id, final T resource);

    // DELETE

    void deleteInternal(final long id);

    // COUNT

    long countInternal();

}
