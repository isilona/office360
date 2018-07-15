package io.office360.common.web.controller;

import io.office360.common.persistence.model.IEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IPagingAndSortingOperationsController<T extends IEntity> {

    List<T> findPaginatedInternal(final int page, final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

    List<T> findSortedInternal(final String sortBy, final String sortOrder);

    List<T> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response);

}